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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLibraryManagementSystemServiceBadCases {

    private OurLibraryManagementSystemService libraryManagementSystemService;
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
    @DisplayName("Try to log in/out with an invalid token, null references, wrong parameter and empty strings")
    public void loginAndLogoutWithANullWrongTokenNullWrongMailAndPassword() throws IllegalParameterException, AccountNotExistException, NoAgreementException, NullPointerException {
        //Add an account to library system
        libraryManagementSystemService.addAccount(accountDescriptorFD);
        //Login with wrong parameter and null
        //Wrong parameter
        IllegalParameterException illegalParameterException1 = assertThrows(IllegalParameterException.class,
                () -> libraryManagementSystemService.login("", ""));
        IllegalParameterException illegalParameterException2 = assertThrows(IllegalParameterException.class,
                () -> libraryManagementSystemService.login("", accountDescriptorFD.getPassword()));
        IllegalParameterException illegalParameterException3 = assertThrows(IllegalParameterException.class,
                () -> libraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), ""));
        AccountNotExistException accountNotExistException = assertThrows(AccountNotExistException.class,
                () -> libraryManagementSystemService.login("Fake@fake.com", accountDescriptorFD.getPassword()));
        NoAgreementException noAgreementException = assertThrows(NoAgreementException.class,
                () -> libraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), "fakefake"));
        //With null
        NullPointerException nullPointerException = assertThrows(NullPointerException.class,
                () -> libraryManagementSystemService.login(null, accountDescriptorFD.getPassword()));
        NullPointerException nullPointerException1 = assertThrows(NullPointerException.class,
                () -> libraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), null));
        //Login and try logout with wrong token and parameter
        libraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), accountDescriptorFD.getPassword());
        Token fakeToken = new Token();
        AccountNotExistException accountNotExistException1 = assertThrows(AccountNotExistException.class,
                () -> libraryManagementSystemService.loginOut(fakeToken));
        NullPointerException nullPointerException2 = assertThrows(NullPointerException.class,
                () -> libraryManagementSystemService.loginOut(null));
    }

    @Test
    @DisplayName("Try to delete account with an invalid and null token")
    public void deleteAccountWithInvalidAndNullToken() throws AccountNotExistException, NullPointerException {
        Token fakeToken = new Token();
        AccountNotExistException accountNotExistException1 = assertThrows(AccountNotExistException.class,
                () -> libraryManagementSystemService.deleteAccount(fakeToken));
        NullPointerException nullPointerException2 = assertThrows(NullPointerException.class,
                () -> libraryManagementSystemService.deleteAccount(null));
    }

    @Test
    @DisplayName("Borrow a book with wrong parameter, null, loaned, lost and wrong and null token")
    public void borrowBookWithWrongParameterNullLoanedLostAndWrongNullToken() throws IllegalParameterException, AccountNotExistException, IllegalStateException, NullPointerException, NoAgreementException {
        //Add account and book to the library system
        libraryManagementSystemService.addAccount(accountDescriptorFD);
        libraryManagementSystemService.addBook(dieHausaufgabe);
        libraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), accountDescriptorFD.getPassword());
        //Borrow with wrong parameter
        IllegalParameterException illegalParameterException1 = assertThrows(IllegalParameterException.class,
                () -> libraryManagementSystemService.borrowBook("9229300114527", accountDescriptorFD.getToken()));
        Token fakeToken = new Token();
        AccountNotExistException accountNotExistException = assertThrows(AccountNotExistException.class,
                () -> libraryManagementSystemService.borrowBook(dieHausaufgabe.getIsbn(), fakeToken));

        //Borrow with null
        NullPointerException nullPointerException1 = assertThrows(NullPointerException.class,
                () -> libraryManagementSystemService.borrowBook(null, accountDescriptorFD.getToken()));
        NullPointerException nullPointerException2 = assertThrows(NullPointerException.class,
                () -> libraryManagementSystemService.borrowBook(dieHausaufgabe.getIsbn(), null));

        //Try to borrow a book what is loaned and lost
        libraryManagementSystemService.borrowBook(dieHausaufgabe.getIsbn(), accountDescriptorFD.getToken());
        IllegalStateException illegalStateException1 = assertThrows(IllegalStateException.class,
                () -> libraryManagementSystemService.borrowBook(dieHausaufgabe.getIsbn(), accountDescriptorFD.getToken()));
        libraryManagementSystemService.bookLost(dieHausaufgabe.getIsbn(), accountDescriptorFD.getToken());
        IllegalStateException illegalStateException2 = assertThrows(IllegalStateException.class,
                () -> libraryManagementSystemService.borrowBook(dieHausaufgabe.getIsbn(), accountDescriptorFD.getToken()));
    }

    @Test
    @DisplayName("Get account with wrong parameter and null")
    public void getAccountWithWrongParameterAndNull() {
        //Create fake token and get account with the fake token and null
        Token fakeToken = new Token();
        AccountNotExistException accountNotExistException = assertThrows(AccountNotExistException.class,
                () -> libraryManagementSystemService.getAccount(fakeToken));
        NullPointerException nullPointerException = assertThrows(NullPointerException.class,
                () -> libraryManagementSystemService.getAccount(null));
    }

    @Test
    @DisplayName("Extend and lost a book with wrong parameter and null")
    public void extendAndLostABookWithWrongParameterAndNull() throws NullPointerException, IllegalParameterException, NoAgreementException, AccountNotExistException, IllegalStateException {
        //Add an account and book to the library system
        libraryManagementSystemService.addAccount(accountDescriptorFD);
        libraryManagementSystemService.addBook(dieHausaufgabe);
        libraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), accountDescriptorFD.getPassword());
        Token fakeToken = new Token();
        //Extend and lost with wrong, empty parameter and wrong token
        IllegalParameterException illegalParameterException1 = assertThrows(IllegalParameterException.class,
                () -> libraryManagementSystemService.extendBorrowingBook("5554446251128", accountDescriptorFD.getToken()));
        IllegalParameterException illegalParameterException2 = assertThrows(IllegalParameterException.class,
                () -> libraryManagementSystemService.extendBorrowingBook("",accountDescriptorFD.getToken()));
        AccountNotExistException accountNotExistException1 = assertThrows(AccountNotExistException.class,
                () -> libraryManagementSystemService.extendBorrowingBook(dieHausaufgabe.getIsbn(), fakeToken));
        IllegalParameterException illegalParameterException4 = assertThrows(IllegalParameterException.class,
                () -> libraryManagementSystemService.bookLost("5554446251128", accountDescriptorFD.getToken()));
        IllegalParameterException illegalParameterException5 = assertThrows(IllegalParameterException.class,
                () -> libraryManagementSystemService.bookLost("", accountDescriptorFD.getToken()));
        AccountNotExistException accountNotExistException2 = assertThrows(AccountNotExistException.class,
                () -> libraryManagementSystemService.bookLost(dieHausaufgabe.getIsbn(), fakeToken));
        //Extend and lost with null for isbn and token
        NullPointerException nullPointerException1 = assertThrows(NullPointerException.class,
                () -> libraryManagementSystemService.extendBorrowingBook(null, accountDescriptorFD.getToken()));
        NullPointerException nullPointerException2 = assertThrows(NullPointerException.class,
                () -> libraryManagementSystemService.extendBorrowingBook(dieHausaufgabe.getIsbn(),null));
        NullPointerException nullPointerException3 = assertThrows(NullPointerException.class,
                () -> libraryManagementSystemService.bookLost(null,accountDescriptorFD.getToken()));
        NullPointerException nullPointerException4 = assertThrows(NullPointerException.class,
                () -> libraryManagementSystemService.bookLost(dieHausaufgabe.getIsbn(),null));
        //Checking with a book with a higher period between due date and borrow date with the notification time
        libraryManagementSystemService.borrowBook(dieHausaufgabe.getIsbn(), accountDescriptorFD.getToken());
        NoAgreementException noAgreementException = assertThrows(NoAgreementException.class,
                () -> libraryManagementSystemService.extendBorrowingBook(dieHausaufgabe.getIsbn(), accountDescriptorFD.getToken()));
    }
}
