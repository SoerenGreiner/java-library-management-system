package de.hhn.it.pp.components.librarymanagementsystem.junit;

import de.hhn.it.pp.components.librarymanagementsystem.exceptions.*;
import de.hhn.it.pp.components.librarymanagementsystem.AccountDescriptor;
import de.hhn.it.pp.components.librarymanagementsystem.MembershipState;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalStateException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NullPointerException;
import de.hhn.it.pp.components.librarymanagementsystem.provider.OurLibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Address;
import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Person;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.BookItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class TestAdminInterfaceGoodCases {
    OurLibraryManagementSystemService adminLibraryManagementSystemService;

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
        adminLibraryManagementSystemService = new OurLibraryManagementSystemService();

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
    @DisplayName("Adds and removes accounts to the library management system ")
    public void addAndRemoveAccountsToTheLibrarySystem() throws IllegalParameterException, NullPointerException, AccountNotExistException, NoAgreementException {
        //Adds 2 accounts and check if array is null and size
        adminLibraryManagementSystemService.addAccount(accountDescriptorTB);
        assertNotNull(adminLibraryManagementSystemService.getAccounts());
        assertEquals(1, adminLibraryManagementSystemService.getAccounts().size());
        adminLibraryManagementSystemService.addAccount(accountDescriptorFD);
        assertEquals(2,adminLibraryManagementSystemService.getAccounts().size());
        //Remove 2 accounts and check results
        adminLibraryManagementSystemService.removeAccount(accountDescriptorTB.getPerson().getEMail());
        assertEquals(1, adminLibraryManagementSystemService.getAccounts().size());
        adminLibraryManagementSystemService.removeAccount(accountDescriptorFD.getPerson().getEMail());
        assertEquals(0,adminLibraryManagementSystemService.getAccounts().size());
    }

    @Test
    @DisplayName("Adds and removes books to the library management system ")
    public void addsAndRemoveBooksToLibraryManagementSystem() throws NullPointerException, IllegalParameterException, IllegalStateException, NoAgreementException {
        //Adds 2 bocks to the library system and check if null and array size
        assertEquals(0, adminLibraryManagementSystemService.getAllBooks().size());
        adminLibraryManagementSystemService.addBook(derAlchimist);
        assertEquals(1, adminLibraryManagementSystemService.getAllBooks().size());
        adminLibraryManagementSystemService.addBook(dieHausaufgabe);
        assertEquals(2, adminLibraryManagementSystemService.getAllBooks().size());
        assertNotNull(adminLibraryManagementSystemService.getAllBooks());
        //Removes 2 books from the library system and check if array size
        adminLibraryManagementSystemService.removeBook(derAlchimist.getIsbn());
        assertEquals(1, adminLibraryManagementSystemService.getAllBooks().size());
        adminLibraryManagementSystemService.removeBook(dieHausaufgabe.getIsbn());
        assertEquals(0, adminLibraryManagementSystemService.getAllBooks().size());
    }

    @Test
    @DisplayName("Try to cancel and accept membership of the account")
    public void cancelAndAcceptMembershipOfTheAccount() throws IllegalParameterException, AccountNotExistException, NullPointerException, NoAgreementException {
        //Add account and check membership state
        adminLibraryManagementSystemService.addAccount(accountDescriptorTB);
        assertEquals(MembershipState.ACCEPTED, accountDescriptorTB.getMembershipState());
        //Cancel membership state of account and check again
        adminLibraryManagementSystemService.cancelMembership(accountDescriptorTB.getPerson().getEMail());
        assertEquals(MembershipState.CANCELED, accountDescriptorTB.getMembershipState());
        //Accept membership state of account and check again
        adminLibraryManagementSystemService.acceptMembership(accountDescriptorTB.getPerson().getEMail());
        assertEquals(MembershipState.ACCEPTED, accountDescriptorTB.getMembershipState());
    }

    @Test
    @DisplayName("Try to get number of not returned books and notify member")
    public void getNotReturnedBooksAndNotifyMember() throws IllegalParameterException, AccountNotExistException, IllegalStateException, NullPointerException, NoAgreementException {
        //Adds 2 accounts to the library system
        adminLibraryManagementSystemService.addAccount(accountDescriptorFD);
        adminLibraryManagementSystemService.addAccount(accountDescriptorVF);
        //Adds books to the library system
        adminLibraryManagementSystemService.addBook(dieHausaufgabe);
        adminLibraryManagementSystemService.addBook(lesMiserables);
        adminLibraryManagementSystemService.addBook(logisticsSupplyChainManagement);
        //Login, borrow the books and check not returned books
        adminLibraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), accountDescriptorFD.getPassword());
        adminLibraryManagementSystemService.borrowBook(dieHausaufgabe.getIsbn(), accountDescriptorFD.getToken());
        adminLibraryManagementSystemService.borrowBook(lesMiserables.getIsbn(), accountDescriptorFD.getToken());;
        assertEquals(2, adminLibraryManagementSystemService.getBorrowedBooks().size());
        //Again with other account
        adminLibraryManagementSystemService.login(accountDescriptorVF.getPerson().getEMail(), accountDescriptorVF.getPassword());
        adminLibraryManagementSystemService.borrowBook(logisticsSupplyChainManagement.getIsbn(),accountDescriptorVF.getToken());
        assertEquals(3, adminLibraryManagementSystemService.getBorrowedBooks().size());
        //Logout and return a book
        adminLibraryManagementSystemService.loginOut(accountDescriptorFD.getToken());
        adminLibraryManagementSystemService.loginOut(accountDescriptorVF.getToken());
        adminLibraryManagementSystemService.bookReturned(logisticsSupplyChainManagement.getIsbn(), accountDescriptorVF.getPerson().getEMail());
        assertEquals(2, adminLibraryManagementSystemService.getBorrowedBooks().size());
        assertEquals(adminLibraryManagementSystemService.getLocalDate(), logisticsSupplyChainManagement.getBookItem().getReturnDate());
        assertEquals(0, accountDescriptorVF.getBorrowedBooks().size());
        //Setting dudate and checking notification for a period between 0 to 3 and after 0
        dieHausaufgabe.getBookItem().setDueDate(dieHausaufgabe.getBookItem().getDueDate().minusDays(4));
        adminLibraryManagementSystemService.setLocalDate(adminLibraryManagementSystemService.getLocalDate().plusDays(12));
        adminLibraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), accountDescriptorFD.getPassword());
        assertEquals(2, adminLibraryManagementSystemService.getBooksToNotify().size());
        assertEquals(-2, dieHausaufgabe.getBookItem().getElapsedDays());
        assertEquals(2, lesMiserables.getBookItem().getElapsedDays());
    }
}
