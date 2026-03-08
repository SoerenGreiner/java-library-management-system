package de.hhn.it.pp.components.librarymanagementsystem.junit;

import de.hhn.it.pp.components.librarymanagementsystem.exceptions.*;
import de.hhn.it.pp.components.librarymanagementsystem.AccountDescriptor;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalStateException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NullPointerException;
import de.hhn.it.pp.components.librarymanagementsystem.provider.OurLibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Address;
import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Person;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.BookItem;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.BookItemState;
import de.hhn.it.pp.components.librarymanagementsystem.provider.tokenFactory.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestLibraryManagementSystemServiceGoodCases {
    OurLibraryManagementSystemService libraryManagementSystemService;
    Address tb;
    Person tudorBusuioc;
    AccountDescriptor accountDescriptorTB;
    Address sg;
    Person soerenGreiner;
    AccountDescriptor accountDescriptorSG;

    Address jw;
    Person joergWinckler;
    AccountDescriptor accountDescriptorJW;

    Address fk;
    Person friedrichKarrle;
    AccountDescriptor accountDescriptorFK;

    Address ma;
    Person michaelAbel;
    AccountDescriptor accountDescriptorMA;

    Address rb;
    Person rebeccaBiebl;
    AccountDescriptor accountDescriptorRB;

    Address fd;
    Person felixDonath;
    AccountDescriptor accountDescriptorFD;

    Address vf;
    Person virginiaFeldmann;
    AccountDescriptor accountDescriptorVF;

    BookItem rM;
    Book researchMethods;

    BookItem eS;
    Book exploringStrategy;

    BookItem pOM;
    Book principlesOfMarketing;

    BookItem dM;
    Book digitalMarketing;

    BookItem aF;
    Book accountingFinance;

    BookItem oM;
    Book operationsManagement;

    BookItem lSCM;
    Book logisticsSupplyChainManagement;

    BookItem lM;
    Book lesMiserables;

    BookItem dH;
    Book dieHausaufgabe;

    BookItem dA;
    Book derAlchimist;


    @BeforeEach
    void setup() throws IllegalParameterException {

        libraryManagementSystemService = new OurLibraryManagementSystemService();

        tb = new Address("Talheimer Strasse 34", "Heilbronn", "Baden Wurttenberg", "74081", "Germany");
        tudorBusuioc = new Person("Tudor Busuioc", "+494364362554", "tudorbusuioc99@gmail.com", tb);
        accountDescriptorTB = new AccountDescriptor("tudor123qwerty", tudorBusuioc);

        sg = new Address("Blummenstrasse 23", "Waldenburg", "Baden Wurttenberg", "74638", "Germany");
        soerenGreiner = new Person("Soeren Greiner", "+49675783665", "soerengreiner434@gmail.com", sg);
        accountDescriptorSG = new AccountDescriptor("secret1234vogel", soerenGreiner);

        jw = new Address("Ulrichstrasse 54", "Stuttgart", "Baden Wurttenberg", "70173", "Germany");
        joergWinckler = new Person("Joerg Winckler", "+4954685648334", " joerg.winckler@hs-heilbronn.de", jw);
        accountDescriptorJW = new AccountDescriptor("geheim5754secretlalal", joergWinckler);

        fk = new Address("Wilchemstrasse 34", "Tuebingen", "Baden Wurttenberg", "72070", "Germany");
        friedrichKarrle = new Person("Friedrich Karrle", "+498486357", "friedrichkarrle34786hs-heilbronn.de", fk);
        accountDescriptorFK = new AccountDescriptor("adler32?!*-blabla", friedrichKarrle);

        ma = new Address("Hebelweg 25 ", "Ettlingen", "Baden Wurttenberg", "76275", "Germany");
        michaelAbel = new Person("Michael Abel", "+49843745354", " michael.abel@hs-heilbronn.de", ma);
        accountDescriptorMA = new AccountDescriptor("pinguin1284?<;", michaelAbel);

        rb = new Address("Heilbronner Strasse 14", "Brackenheim", "Baden Wurttenberg", "74336", "Germany");
        rebeccaBiebl = new Person("Rebecca Biebl", "+495837326478", "rebecca.biebl@hs-heilbronn.de", rb);
        accountDescriptorRB = new AccountDescriptor("milkyway7593horizont", rebeccaBiebl);

        fd = new Address("Rumfordstrasse 5 ", "Munich", "Bayern", "80331", "Germany");
        felixDonath = new Person("Felix Donath", "+4942789434775", " felix.donath@hs-heilbronn.de", fd);
        accountDescriptorFD = new AccountDescriptor("veryhardtohack99246", felixDonath);

        vf = new Address("Markststrasse 18", "Heidelberg", "Baden Wurttenberg", "69115", "Germany");
        virginiaFeldmann = new Person("Virginia Feldmann", "+4983563728425", "virginia.feldmann@hs-heilbronn.de", vf);
        accountDescriptorVF = new AccountDescriptor("fox548/'[schlau", virginiaFeldmann);


        rM = new BookItem();
        researchMethods = new Book("Research Methods for Business Students", "Business",
                "Pearson", "English", "9781292208794", 872, rM);

        eS = new BookItem();
        exploringStrategy = new Book("Exploring Strategy", "Business Strategy", "Pearson",
                "English", "9781292282503", 840, eS);

        pOM = new BookItem();
        principlesOfMarketing = new Book("Principles of Marketing", "Marketing",
                "Pearson", "English", "9781292220239", 736, pOM);

        dM = new BookItem();
        digitalMarketing = new Book("Digital Marketing", "Marketing", "Pearson",
                "English", "9781292241623", 576, dM);

        aF = new BookItem();
        accountingFinance = new Book("Accounting and Finance for Non-Specialists", "Accounting",
                "Pearson", "English", "9781292244051", 616, aF);

        oM = new BookItem();
        operationsManagement = new Book("Operations Management", "Operations", "Pearson",
                "English", "9781292253992", 776, oM);

        lSCM = new BookItem();
        logisticsSupplyChainManagement = new Book("Logistics & Supply Chain Management",
                "Business", "Pearson", "English", "9781292083810", 328, lSCM);

        lM = new BookItem();
        lesMiserables = new Book("Les Misérables", "Historical fiction", "Penguin Classics",
                "English", "9780140444308", 1232, lM);

        dH = new BookItem();
        dieHausaufgabe = new Book("Die Hausaufgabe", "History", "Buchwerkstatt Berlin",
                "Deutsch", "3946467474", 300, dH);

        dA = new BookItem();
        derAlchimist = new Book("Der Alchimist", "Life and Universe", "Diogenes Verlag, Zürich",
                "Deutsch", "3257061269", 172, dA);
    }

    @Test
    @DisplayName("Try to log in and log out")
    public void tryToLoginLogout() throws IllegalParameterException, AccountNotExistException, NoAgreementException, NullPointerException {
        libraryManagementSystemService.addAccount(accountDescriptorFD);
        libraryManagementSystemService.addAccount(accountDescriptorFK);
        //Check if account token is null for login
        assertEquals(accountDescriptorFD.getToken(), null);
        assertEquals(accountDescriptorFK.getToken(), null);
        //Login
        libraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), accountDescriptorFD.getPassword());
        libraryManagementSystemService.login(accountDescriptorFK.getPerson().getEMail(), accountDescriptorFK.getPassword());
        //Check if Token is not null and didn't have the same token
        assertNotNull(accountDescriptorFD.getToken());
        assertNotNull(accountDescriptorFK.getToken());
        assertNotEquals(accountDescriptorFD, accountDescriptorFK);
        //Login out
        libraryManagementSystemService.loginOut(accountDescriptorFD.getToken());
        libraryManagementSystemService.loginOut(accountDescriptorFK.getToken());
        //Check if token is again null
        assertEquals(accountDescriptorFD.getToken(), null);
        assertEquals(accountDescriptorFK.getToken(), null);
    }

    @Test
    @DisplayName("Try to borrow books, extend the borrowing, set them lost and get one returned.")
    public void borrowBooksExtendSetThemLostAndReturn() throws IllegalParameterException, AccountNotExistException, IllegalStateException, NoAgreementException, NullPointerException {
        //Add 2 accounts to the library system
        libraryManagementSystemService.addAccount(accountDescriptorFD);
        libraryManagementSystemService.addAccount(accountDescriptorFK);
        //Add 4 books to the library system
        libraryManagementSystemService.addBook(dieHausaufgabe);
        libraryManagementSystemService.addBook(lesMiserables);
        libraryManagementSystemService.addBook(logisticsSupplyChainManagement);
        libraryManagementSystemService.addBook(digitalMarketing);
        //Check if books added
        assertEquals(4, libraryManagementSystemService.getAllBooks().size());
        //Login the 2 accounts
        libraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), accountDescriptorFD.getPassword());
        libraryManagementSystemService.login(accountDescriptorFK.getPerson().getEMail(), accountDescriptorFK.getPassword());
        //Borrow the 4 books and check
        libraryManagementSystemService.borrowBook(dieHausaufgabe.getIsbn(), accountDescriptorFD.getToken());
        assertEquals(1, accountDescriptorFD.getBorrowedBooks().size());
        libraryManagementSystemService.borrowBook(lesMiserables.getIsbn(), accountDescriptorFD.getToken());
        assertEquals(2, accountDescriptorFD.getBorrowedBooks().size());
        libraryManagementSystemService.borrowBook(logisticsSupplyChainManagement.getIsbn(), accountDescriptorFK.getToken());
        assertEquals(1, accountDescriptorFK.getBorrowedBooks().size());
        libraryManagementSystemService.borrowBook(digitalMarketing.getIsbn(), accountDescriptorFK.getToken());
        assertEquals(2, accountDescriptorFK.getBorrowedBooks().size());
        //Check if all 4 books are loaned
        assertEquals(BookItemState.LOANED, dieHausaufgabe.getBookItem().getBookStatus());
        assertEquals(BookItemState.LOANED, lesMiserables.getBookItem().getBookStatus());
        assertEquals(BookItemState.LOANED, logisticsSupplyChainManagement.getBookItem().getBookStatus());
        assertEquals(BookItemState.LOANED, digitalMarketing.getBookItem().getBookStatus());
        //Extend 1 book and check if it was extended
        assertEquals(libraryManagementSystemService.getLocalDate().plusDays(14), dieHausaufgabe.getBookItem().getDueDate());
        libraryManagementSystemService.setLocalDate(libraryManagementSystemService.getLocalDate().plusDays(11));
        libraryManagementSystemService.extendBorrowingBook(dieHausaufgabe.getIsbn(), accountDescriptorFD.getToken());
        assertEquals(libraryManagementSystemService.getLocalDate().plusDays(14), dieHausaufgabe.getDueDate());
        //Set one book lost and check state and array
        libraryManagementSystemService.bookLost(logisticsSupplyChainManagement.getIsbn(), accountDescriptorFK.getToken());
        assertEquals(BookItemState.LOST, logisticsSupplyChainManagement.getBookItem().getBookStatus());
    }

    @Test
    @DisplayName("Try to delete account")
    public void deleteAccount() throws IllegalParameterException, NullPointerException, AccountNotExistException, NoAgreementException {
        //Add 2 accounts to the library system
        libraryManagementSystemService.addAccount(accountDescriptorFD);
        libraryManagementSystemService.addAccount(accountDescriptorFK);
        //Check if two accounts added
        assertEquals(2, libraryManagementSystemService.getAccounts().size());
        //Login in accounts
        libraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), accountDescriptorFD.getPassword());
        libraryManagementSystemService.login(accountDescriptorFK.getPerson().getEMail(), accountDescriptorFK.getPassword());
        //Check if accounts have a token
        assertNotNull(accountDescriptorFD.getToken());
        assertNotNull(accountDescriptorFK.getToken());
        //Delete accounts one bye one and check
        libraryManagementSystemService.deleteAccount(accountDescriptorFD.getToken());
        assertEquals(1,libraryManagementSystemService.getAccounts().size());
        libraryManagementSystemService.deleteAccount(accountDescriptorFK.getToken());
        assertEquals(0, libraryManagementSystemService.getAccounts().size());
    }

    @Test
    @DisplayName("Try to remove the registered listeners")
    public void search() throws IllegalParameterException, AccountNotExistException, NoAgreementException, NullPointerException {
        //Add an account to the library system
        libraryManagementSystemService.addAccount(accountDescriptorFD);
        //Adds 2 books to the library system
        libraryManagementSystemService.addBook(principlesOfMarketing);
        libraryManagementSystemService.addBook(digitalMarketing);
        //Login and search for books and check if they added to the found book array
        libraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), accountDescriptorFD.getPassword());
        libraryManagementSystemService.search("Marketing");
        assertEquals(2, libraryManagementSystemService.getFoundBooks().size());
    }
}
