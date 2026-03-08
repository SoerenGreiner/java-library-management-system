package de.hhn.it.pp.components.librarymanagementsystem.provider;

import de.hhn.it.pp.components.librarymanagementsystem.exceptions.*;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalStateException;
import de.hhn.it.pp.components.librarymanagementsystem.helper.CheckingHelper;
import de.hhn.it.pp.components.librarymanagementsystem.AccountDescriptor;
import de.hhn.it.pp.components.librarymanagementsystem.LibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.MembershipState;
import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Account;
import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.OurAccount;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.BookItemState;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Searchable;
import de.hhn.it.pp.components.librarymanagementsystem.provider.notificationmanagement.EmailNotification;
import de.hhn.it.pp.components.librarymanagementsystem.provider.notificationmanagement.PostalNotification;
import de.hhn.it.pp.components.librarymanagementsystem.provider.tokenFactory.Token;
import de.hhn.it.pp.components.librarymanagementsystem.provider.tokenFactory.TokenFactory;
import de.hhn.it.pp.components.librarymanagementsystem.provider.tokenFactory.UserTokenFactory;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NullPointerException;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;


public class OurLibraryManagementSystemService implements LibraryManagementSystemService, AdminLibraryManagementSystemService, Searchable {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(OurLibraryManagementSystemService.class);

    private Map<String, OurAccount> accounts;
    private List<Book> libraryBooks;
    private List<Book> foundBooks;
    private List<Book> booksToNotify;
    private LocalDate localDate;
    protected TokenFactory factory;
    private int notificationTime = 3;

    public OurLibraryManagementSystemService() throws IllegalParameterException {
        this.localDate = LocalDate.now();
        this.accounts = new HashMap<>();
        this.libraryBooks = new ArrayList<>();
        this.factory = new UserTokenFactory();
        this.foundBooks = new ArrayList<>();
        this.booksToNotify = new ArrayList<>();
        logger.info("Lokal Date is : {}", localDate);
    }

    private Account getAccountByMail(String eMail) throws NullPointerException, AccountNotExistException {
        logger.info("getAccountByEMail: accountEMail = {}", eMail);
        if (eMail == null) {
            throw new NullPointerException("Parameter can not be a null reference");
        }
        if (!accounts.containsKey(eMail)) {
            throw new AccountNotExistException("Account with eMail " + eMail + " does not exist.");
        }
        return accounts.get(eMail);
    }

    private Account getAccountByToken(Token token) throws AccountNotExistException, NullPointerException {
        logger.info("getAccountByToken: accountToken = {}", token);
        if (token == null) {
            throw new NullPointerException("Parameter can not be a null reference");
        }

        List<OurAccount> accountDescriptors = new ArrayList<>(accounts.values());
        for (OurAccount account : accountDescriptors) {
            if (account.getDescriptor().getToken() == token) {
                return account;
            }
        }
        throw new AccountNotExistException("Account with token " + token + " does not exist.");
    }

    private Book getBookByISBN(String isbn) throws IllegalParameterException, NullPointerException {
        logger.info("getBookByISBN: bookISBN = {}", isbn);
        if (isbn == null) {
            throw new NullPointerException("Isbn can not be a null reference");
        }
        for (Book book : libraryBooks) {
            if (book.getIsbn() == isbn) {
                return book;
            }
        }
        throw new IllegalParameterException("The book is not known in the library management system");
    }

    // -----------------------------------------------------------------
    // --- Library Management System: All methods of the casual user ---
    // -----------------------------------------------------------------

    // >>>>> Account logic <<<<<

    @Override
    public void login(String eMail, String password) throws IllegalParameterException, NoAgreementException, NullPointerException, AccountNotExistException {
        logger.info("Login in: eMail = {}", eMail);
        CheckingHelper.assertThatIsReadableString(eMail, "mail");
        CheckingHelper.assertThatIsReadableString(password, "password");
        Account account = getAccountByMail(eMail);
        if (account.getDescriptor().getPassword().equals(password)) {
            // Creation of the token
            Token token = factory.createToken(account);
            account.getDescriptor().setToken(token);
            notifyMember(account.getDescriptor().getToken());
        } else {
            throw new NoAgreementException("Entered password " + password + " was wrong, please try again!");
        }
    }

    @Override
    public void loginOut(Token token) throws NullPointerException, AccountNotExistException {
        logger.info("login out of the account: token = {}", token);
        CheckingHelper.assertThatIsNotNull(token,"token");
        Account account = getAccountByToken(token);
        account.getDescriptor().setToken(null);
    }

    @Override
    public AccountDescriptor getAccount(Token token) throws NullPointerException, AccountNotExistException {
        logger.info("getAccount: token = {}", token);
        CheckingHelper.assertThatIsNotNull(token,"token");

        Account account = getAccountByToken(token);
        return account.getDescriptor();
    }

    @Override
    public void deleteAccount(Token token) throws NullPointerException, AccountNotExistException {
        logger.info("deletes the account: token {}", token);
        CheckingHelper.assertThatIsNotNull(token,"token");

        Account account = getAccountByToken(token);
        accounts.remove(account.getDescriptor().getPerson().getEMail());
    }

    public void setLocalDate(LocalDate localDate) {
        logger.info("sets the current date: current date = {}", localDate);
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        logger.info("get the current date");
        return localDate;
    }

    @Override
    public List<Book> getBooksToNotify() {
        logger.info("get all books that are to be notify for the account");
        return booksToNotify;
    }

    // >>>>> Library logic <<<<<
    @Override
    public void borrowBook(String isbn, Token token) throws IllegalParameterException, IllegalStateException, NullPointerException, AccountNotExistException {
        logger.info("borrows the book: isbn = {}, token = {}", isbn, token);
        CheckingHelper.assertThatIsReadableString(isbn,"isbn");
        CheckingHelper.assertThatIsNotNull(token,"token");
        Account account = getAccountByToken(token);
        Book book = getBookByISBN(isbn);
        if (book.getBookItem().getBookStatus() == BookItemState.AVAILABLE) {
            book.getBookItem().setBookStatus(BookItemState.LOANED);
            book.getBookItem().setBorrowDate(localDate);
            book.getBookItem().setDueDate(localDate.plusDays(+14));
            book.getBookItem().setElapsedDays(14);
            account.getDescriptor().setBorrowedBook(book);
        } else {
            throw new IllegalStateException("The book is not available in the library");
        }
    }

    @Override
    public Book getBook(String isbn) throws IllegalParameterException, NullPointerException {
        logger.info("returns the book with the referenced isbn: isbn {} ", isbn);
        CheckingHelper.assertThatIsReadableString(isbn,"isbn");
        Book book = getBookByISBN(isbn);
        return book;
    }

    @Override
    public List<Book> getFoundBooks() {
        logger.info("returns all found books: {}", foundBooks);
        return foundBooks;
    }

    public List<Book> getAllBooks() {
        logger.info("Returns all books in the library");
        return libraryBooks;
    }

    @Override
    public void search(String string) {
        logger.info("search books by string: string = {} ", string);

        if (foundBooks != null) {
            foundBooks.removeAll(foundBooks);
        }
        searchByTitle(string);
        searchByPublisher(string);
        searchBySubject(string);
    }

    @Override
    public void bookLost(String isbn, Token token) throws NullPointerException, IllegalParameterException, AccountNotExistException {
        logger.info("Sets the book lost with isbn: {}", isbn);
        CheckingHelper.assertThatIsReadableString(isbn,"isbn");
        CheckingHelper.assertThatIsNotNull(token,"token");

        Account account = getAccountByToken(token);
        Book book = getBookByISBN(isbn);
        List<Book> borrowedBooks = account.getDescriptor().getBorrowedBooks();
        for (Book actualBorrowedBook : borrowedBooks) {
            if (actualBorrowedBook.getIsbn() == book.getIsbn()) {
                book.getBookItem().setBookStatus(BookItemState.LOST);
            }
        }
    }


    @Override
    public void extendBorrowingBook(String isbn, Token token) throws IllegalParameterException, NullPointerException, AccountNotExistException, NoAgreementException {
        logger.info("Extends borrowing of the book with isbn: {}", isbn);
        CheckingHelper.assertThatIsReadableString(isbn,"isbn");
        CheckingHelper.assertThatIsNotNull(token,"token");

        Account account = getAccountByToken(token);
        Book book = getBookByISBN(isbn);
        if (Period.between(localDate, book.getBookItem().getDueDate()).getDays() <= notificationTime) {
            List<Book> borrowedBooks = account.getDescriptor().getBorrowedBooks();
            for (Book actualBorrowedBook : borrowedBooks) {
                if (actualBorrowedBook.getIsbn().equals(book.getIsbn())) {
                    book.getBookItem().setDueDate(localDate.plusDays(14));
                    notifyMember(token);
                }
            }
        }
        else {
            throw new NoAgreementException("Period between due date and time to get notified is to long, " +
                    "you can't extend the book at the moment");
        }

    }

    // --------------------------------------------------------------------------
    // --- Admin Library Management System: All methods of the librarian user ---
    // --------------------------------------------------------------------------

    // >>>>> Account logic <<<<<
    @Override
    public List<AccountDescriptor> getAccounts() {
        logger.info("getAccounts: no params");
        List<AccountDescriptor> results = new ArrayList<>();
        for (OurAccount account : accounts.values()) {
            results.add(account.getDescriptor());
        }
        return results;
    }

    @Override
    public AccountDescriptor getAccountByAdmin(final String eMail) throws NullPointerException, AccountNotExistException {
        logger.info("getAccount: eMail = {}", eMail);
        Account account = getAccountByMail(eMail);
        return account.getDescriptor();
    }

    @Override
    public void addAccount(final AccountDescriptor descriptor) throws IllegalParameterException, NullPointerException, NoAgreementException {
        logger.info("addAccount: descriptor = {}", descriptor);
        CheckingHelper.assertThatIsNotNull(descriptor, "descriptor");
        CheckingHelper.assertThatIsReadableString(descriptor.getPassword(), "password");
        CheckingHelper.assertThatIsReadableString(descriptor.getPerson().getEMail(), "mail");
        CheckingHelper.assertThatIsReadableString(descriptor.getPerson().getName(), "name");
        CheckingHelper.assertThatIsReadableString(descriptor.getPerson().getPhoneNumber(), "phone number");
        CheckingHelper.assertThatIsReadableString(descriptor.getPerson().getAddress().getStreetAddress(), "street address");
        CheckingHelper.assertThatIsReadableString(descriptor.getPerson().getAddress().getCity(), "city");
        CheckingHelper.assertThatIsReadableString(descriptor.getPerson().getAddress().getZipCode(), "zip code");
        CheckingHelper.assertThatIsReadableString(descriptor.getPerson().getAddress().getCountry(), "country");
        CheckingHelper.assertThatIsReadableString(descriptor.getPerson().getAddress().getState(), "state");

        OurAccount ourAccount = new OurAccount(descriptor);
        if (accounts.containsKey(descriptor.getPerson().getEMail())) {
            throw new NoAgreementException("Account with eMail " + descriptor.getPerson().getEMail() + " already exist");
        } else {
            accounts.put(ourAccount.getDescriptor().getPerson().getEMail(), ourAccount);
        }
    }

    @Override
    public void removeAccount(String eMail) throws NullPointerException, AccountNotExistException {
        logger.info("removeAccount with eMail = {}", eMail);
        Account account = getAccountByMail(eMail);
        accounts.remove(account.getDescriptor().getPerson().getEMail());
    }

    @Override
    public void acceptMembership(String eMail) throws NullPointerException, AccountNotExistException {
        logger.info("accepts the membership of the account: mail {}", eMail);
        Account account = getAccountByMail(eMail);
        account.getDescriptor().setMembershipState(MembershipState.ACCEPTED);
    }

    @Override
    public void cancelMembership(String eMail) throws NullPointerException, AccountNotExistException {
        logger.info("cancels the membership of the account: eMail {}", eMail);
        Account account = getAccountByMail(eMail);
        account.getDescriptor().setMembershipState(MembershipState.CANCELED);
    }

    // >>>>> Library logic <<<<<
    @Override
    public void addBook(Book book) throws NullPointerException, IllegalParameterException, NoAgreementException {
        logger.info("adds a book to the library: book = {} ", book);
        CheckingHelper.assertThatIsNotNull(book, "book");
        CheckingHelper.assertThatIsReadableString(book.getTitle(), "title");
        CheckingHelper.assertThatIsReadableString(book.getSubject(), "subject");
        CheckingHelper.assertThatIsReadableString(book.getPublisher(), "publisher");
        CheckingHelper.assertThatIsReadableString(book.getLanguage(), "language");
        CheckingHelper.assertThatIsReadableString(book.getIsbn(), "isbn");
        CheckingHelper.assertThatIsReadableInteger(book.getNumberOfPages(), "numberOfPages");
        CheckingHelper.assertThatIsNotNull(book.getBookItem(), "bookItems");

        if (libraryBooks.contains(book)) {
            throw new NoAgreementException("Book with isbn " + book.getIsbn() + " already exist");
        } else {
            libraryBooks.add(book);
        }

    }

    @Override
    public void removeBook(String isbn) throws
            IllegalParameterException, IllegalStateException, NullPointerException {
        logger.info("removes the book: isbn = {} ", isbn);
        Book book = getBookByISBN(isbn);
        if (book.getBookItem().getBookStatus() == BookItemState.AVAILABLE) {
            libraryBooks.remove(this.getBook(isbn));
        } else {
            throw new IllegalStateException("Book with the isbn: " + isbn + "is currently not available in the library and can not be removed");
        }
    }

    @Override
    public List<Book> getBorrowedBooks() {
        logger.info("returns the books that are not returned to the library ");

        List<Book> notReturnedBooks = new ArrayList<>();
        for (Book book : libraryBooks) {
            if (book.getBookItem().getBookStatus() != BookItemState.AVAILABLE) {
                notReturnedBooks.add(book);
            }
        }
        return notReturnedBooks;
    }

    @Override
    public void notifyMember(Token token) throws NullPointerException, AccountNotExistException {
        logger.info("Notifies the member: Token = {} ", token);

        Account account = getAccountByToken(token);
        List<Book> borrowedBooks = account.getDescriptor().getBorrowedBooks();
        for (Book book : borrowedBooks) {
            if (Period.between(localDate, book.getBookItem().getDueDate()).getDays() > notificationTime & booksToNotify.contains(book)) {
                booksToNotify.remove(book);
            }
            if (Period.between(localDate, book.getBookItem().getDueDate()).getDays() <= notificationTime) {
                book.getBookItem().setElapsedDays(Period.between(localDate, book.getBookItem().getDueDate()).getDays());
                EmailNotification emailNotification = new EmailNotification(book, account.getDescriptor().getPerson());
                emailNotification.sendNotification();

                PostalNotification postalNotification = new PostalNotification(book, account.getDescriptor().getPerson());
                postalNotification.sendNotification();
                if (!booksToNotify.contains(book)) {
                    booksToNotify.add(book);
                }
            }
        }
    }

    @Override
    public void bookReturned(String isbn, String eMail) throws IllegalParameterException, IllegalStateException, NullPointerException, AccountNotExistException {
        logger.info("returns the book: isbn = {}, eMail = {} ", isbn, eMail);
        CheckingHelper.assertThatIsReadableString(isbn,"isbn");
        CheckingHelper.assertThatIsReadableString(eMail,"mail");
        Account account = getAccountByMail(eMail);
        Book book = getBookByISBN(isbn);
        if (book.getBookItem().getBookStatus() == BookItemState.AVAILABLE || book.getBookItem().getBookStatus() == BookItemState.LOST) {
            throw new IllegalStateException("Book with the isbn: " + isbn + " is available or lost and can't be returned!");
        } else {
            book.getBookItem().setBookStatus(BookItemState.AVAILABLE);
            book.getBookItem().setReturnDate(localDate);
            account.getDescriptor().getBorrowedBooks().remove(book);
        }
    }

// --------------------------------------------------------------------------
// --- Catalog ---
// --------------------------------------------------------------------------

    @Override
    public void searchByTitle(String title) {
        logger.info("search books by title: title = {} ", title);

        for (Book book : libraryBooks) {
            if (book.getTitle().contains(title)) {
                if (!foundBooks.contains(book)) {
                    foundBooks.add(book);
                }
            }
        }
    }

    @Override
    public void searchByPublisher(String publisher) {
        logger.info("search books by publisher: publisher = {} ", publisher);

        for (Book book : libraryBooks) {
            if (book.getPublisher().contains(publisher)) {
                if (!foundBooks.contains(book)) {
                    foundBooks.add(book);
                }
            }
        }
    }

    @Override
    public void searchBySubject(String subject) {
        logger.info("search book by subject: subject = {} ", subject);

        for (Book book : libraryBooks) {
            if (book.getSubject().contains(subject)) {
                if (!foundBooks.contains(book)) {
                    foundBooks.add(book);
                }
            }
        }
    }
}
