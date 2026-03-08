package de.hhn.it.pp.components.librarymanagementsystem.provider;

import de.hhn.it.pp.components.librarymanagementsystem.exceptions.*;
import de.hhn.it.pp.components.librarymanagementsystem.AccountDescriptor;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalStateException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NullPointerException;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;
import de.hhn.it.pp.components.librarymanagementsystem.provider.tokenFactory.Token;

import java.util.List;

/**
 * Admin interface to the OurLibraryManagementSystemService to add / remove Accounts to / from the service.
 */
public interface AdminLibraryManagementSystemService {

    /**
     * Returns a list of registered accounts.
     *
     * @return List of registered accounts
     */
    List<AccountDescriptor> getAccounts();

    /**
     * Returns the descriptor of the account with the given eMail.
     *
     * @param eMail eMail of the account
     * @return descriptor of the account
     * @throws IllegalParameterException if the eMail is a null reference
     * @throws AccountNotExistException  if the account didn't exist
     */
    AccountDescriptor getAccountByAdmin(String eMail) throws IllegalParameterException, AccountNotExistException, NullPointerException;

    /**
     * Adds an new account to the service.
     *
     * @param descriptor descriptor of the new account
     * @throws IllegalParameterException if the descriptor is a null reference or incomplete
     */
    void addAccount(AccountDescriptor descriptor) throws IllegalParameterException, NullPointerException, NoAgreementException;

    /**
     * Removes an account from the service.
     *
     * @param eMail eMail of the account, which account must be removed
     * @throws IllegalParameterException if the eMail is a null reference
     * @throws AccountNotExistException  if the account doesn't exist
     */
    void removeAccount(String eMail) throws IllegalParameterException, AccountNotExistException, NullPointerException;

    /**
     * Accepts the membership of the account.
     *
     * @param eMail Mail assigned to the account/ borrower
     * @throws IllegalParameterException if the parameter is a null reference
     * @throws AccountNotExistException  if the account didn't exist
     */
    void acceptMembership(String eMail) throws IllegalParameterException, NullPointerException, AccountNotExistException;

    /**
     * Cancels the membership of the account.
     *
     * @param eMail eMail assigned to the account/ borrower
     * @throws IllegalParameterException if the token is a null reference
     * @throws AccountNotExistException  if the account doesn't exist
     */
    void cancelMembership(String eMail) throws IllegalParameterException, AccountNotExistException, NullPointerException;

    /**
     * Adds a book to the library management system.
     *
     * @param book item for the library
     * @throws IllegalParameterException if the book is a null reference
     */
    void addBook(Book book) throws IllegalParameterException, NullPointerException, NoAgreementException;

    /**
     * Removes a book from the library management system.
     *
     * @param isbn id of the book
     * @throws IllegalParameterException if the given book isbn is a null reference
     * @throws IllegalStateException book not available in the library
     * @throws NoAgreementException if the book isbn doesn't agree with the bookItem isbn
     */
    void removeBook(String isbn) throws IllegalParameterException, IllegalStateException, NoAgreementException, NullPointerException;

    /**
     * Returns all the books which have not been returned.
     *
     * @return all the books which have not been returned
     */
    List<Book> getBorrowedBooks();

    /**
     * Notifies the member (3 days before deadline) when they must return the books.
     *
     * @param token id of the logged in member
     * @throws IllegalParameterException if the isbn or eMail is a null reference
     * @throws AccountNotExistException  if the account doesn't exist
     */
    void notifyMember(Token token) throws IllegalParameterException, AccountNotExistException, NoAgreementException, NullPointerException;

    /**
     * Returns a book to the library.
     *
     * @param isbn  id of the book
     * @param eMail eMail assigned to the account/ borrower
     * @throws IllegalParameterException if the parameters are null reference
     * @throws IllegalStateException book not available in the library
     * @throws AccountNotExistException  if the account doesn't exist
     */
    void bookReturned(String isbn, String eMail) throws IllegalParameterException, IllegalStateException, AccountNotExistException, NoAgreementException, NullPointerException;
}

