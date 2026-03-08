package de.hhn.it.pp.components.librarymanagementsystem;

import de.hhn.it.pp.components.librarymanagementsystem.exceptions.*;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalStateException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NullPointerException;
import de.hhn.it.pp.components.librarymanagementsystem.provider.AdminLibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.provider.OurLibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Address;
import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Person;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.BookItem;
import de.hhn.it.pp.components.librarymanagementsystem.provider.tokenFactory.Token;


public class LibraryManagementSystemUsageDemo {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(LibraryManagementSystemUsageDemo.class);

    public static void main(String[] args) throws IllegalParameterException, InterruptedException, AccountNotExistException, IllegalStateException, NoAgreementException, NullPointerException {

        OurLibraryManagementSystemService ourlibraryManagementSystemService = new OurLibraryManagementSystemService();
        AdminLibraryManagementSystemService adminLibraryManagementSystemService = ourlibraryManagementSystemService;
        LibraryManagementSystemService libraryManagementSystemService = ourlibraryManagementSystemService;

        // add books to the library management system
        Book researchMethods = new Book("Research Methods for Business Students", "Business",
                "Pearson", "English", "9781292208794", 872, new BookItem());
        ourlibraryManagementSystemService.addBook(researchMethods);

        Book exploringStrategy = new Book("Exploring Strategy", "Business Strategy", "Pearson",
                "English", "9781292282503", 840,new BookItem());
        ourlibraryManagementSystemService.addBook(exploringStrategy);

        Book principlesOfMarketing = new Book("Principles of Marketing", "Marketing",
                "Pearson", "English", "9781292220239", 736, new BookItem());
        ourlibraryManagementSystemService.addBook(principlesOfMarketing);

        Book digitalMarketing = new Book("Digital Marketing", "Marketing", "Pearson",
                "English", "9781292241623", 576, new BookItem());
        ourlibraryManagementSystemService.addBook(digitalMarketing);

        Book accountingFinance = new Book("Accounting and Finance for Non-Specialists", "Accounting",
                "Pearson", "English", "9781292244051", 616, new BookItem());
        ourlibraryManagementSystemService.addBook(accountingFinance);

        Book operationsManagement = new Book("Operations Management", "Operations", "Pearson",
                "English", "9781292253992", 776, new BookItem());
        ourlibraryManagementSystemService.addBook(operationsManagement);

        Book logisticsSupplyChainManagement = new Book("Logistics & Supply Chain Management",
                "Business", "Pearson", "English", "9781292083810", 328, new BookItem());
        ourlibraryManagementSystemService.addBook(logisticsSupplyChainManagement);

        Book lesMiserables = new Book("Les Misérables", "Historical fiction", "Penguin Classics",
                "English", "9780140444308", 1232, new BookItem());
        ourlibraryManagementSystemService.addBook(lesMiserables);

        Book dieHausaufgabe = new Book("Die Hausaufgabe", "History", "Buchwerkstatt Berlin",
                "Deutsch", "3946467474", 300, new BookItem());
        ourlibraryManagementSystemService.addBook(dieHausaufgabe);

        Book derAlchimist = new Book("Der Alchimist", "Life and Universe", "Diogenes Verlag, Zürich",
                "Deutsch", "3257061269", 172, new BookItem());

        ourlibraryManagementSystemService.addBook(derAlchimist);


        // register accounts via admin interface
        Address tb = new Address("Talheimer Strasse 34", "Heilbronn", "Baden Wurttenberg", "74081", "Germany");
        Person tudorBusuioc = new Person("Tudor Busuioc", "+494364362554", "tudorbusuioc99@gmail.com", tb);
        AccountDescriptor accountDescriptorTB = new AccountDescriptor("tudor123qwerty", tudorBusuioc);
        adminLibraryManagementSystemService.addAccount(accountDescriptorTB);


        Address sg = new Address("Blummenstrasse 23", "Waldenburg", "Baden Wurttenberg", "74638", "Germany");
        Person soerenGreiner = new Person("Soeren Greiner", "+49675783665", "soerengreiner434@gmail.com", sg);
        AccountDescriptor accountDescriptorSG = new AccountDescriptor("secret1234vogel", soerenGreiner);
        adminLibraryManagementSystemService.addAccount(accountDescriptorSG);

        Address jw = new Address("Ulrichstrasse 54", "Stuttgart", "Baden Wurttenberg", "70173", "Germany");
        Person joergWinckler = new Person("Joerg Winckler", "+4954685648334", " joerg.winckler@hs-heilbronn.de", jw);
        AccountDescriptor accountDescriptorJW = new AccountDescriptor("geheim5754secretlalal", joergWinckler);
        adminLibraryManagementSystemService.addAccount(accountDescriptorJW);

        Address fk = new Address("Wilchemstrasse 34", "Tuebingen", "Baden Wurttenberg", "72070", "Germany");
        Person friedrichKarrle = new Person("Friedrich Karrle", "+498486357", "friedrichkarrle34786hs-heilbronn.de", fk);
        AccountDescriptor accountDescriptorFK = new AccountDescriptor("adler32?!*-blabla", friedrichKarrle);
        adminLibraryManagementSystemService.addAccount(accountDescriptorFK);

        Address ma = new Address("Hebelweg 25 ", "Ettlingen", "Baden Wurttenberg", "76275", "Germany");
        Person michaelAbel = new Person("Michael Abel", "+49843745354", " michael.abel@hs-heilbronn.de", ma);
        AccountDescriptor accountDescriptorMA = new AccountDescriptor("pinguin1284?<;", michaelAbel);
        adminLibraryManagementSystemService.addAccount(accountDescriptorMA);

        Address rb = new Address("Heilbronner Strasse 14", "Brackenheim", "Baden Wurttenberg", "74336", "Germany");
        Person rebeccaBiebl = new Person("Rebecca Biebl", "+495837326478", "rebecca.biebl@hs-heilbronn.de", rb);
        AccountDescriptor accountDescriptorRB = new AccountDescriptor("milkyway7593horizont", rebeccaBiebl);
        adminLibraryManagementSystemService.addAccount(accountDescriptorRB);

        Address fd = new Address("Rumfordstrasse 5 ", "Munich", "Bayern", "80331", "Germany");
        Person felixDonath = new Person("Felix Donath", "+4942789434775", " felix.donath@hs-heilbronn.de", fd);
        AccountDescriptor accountDescriptorFD = new AccountDescriptor("veryhardtohack99246", felixDonath);
        adminLibraryManagementSystemService.addAccount(accountDescriptorFD);

        Address vf = new Address("Markststrasse 18", "Heidelberg", "Baden Wurttenberg", "69115", "Germany");
        Person virginiaFeldmann = new Person("Virginia Feldmann", "+4983563728425", "virginia.feldmann@hs-heilbronn.de", vf);
        AccountDescriptor accountDescriptorVF = new AccountDescriptor("fox548/'[schlau", virginiaFeldmann);
        adminLibraryManagementSystemService.addAccount(accountDescriptorVF);

        //Returns all books available in the library
        logger.info("--> :All books available in the library");
        for (Book book : ourlibraryManagementSystemService.getAllBooks()) {
            logger.info(book.getTitle());
        }

        //Remove some books
        logger.info("Remove some books");
        ourlibraryManagementSystemService.removeBook(lesMiserables.getIsbn());

        //Returns all clients/accounts known in the library
        logger.info("-->: All accounts known in the library");
        for (AccountDescriptor accountDescriptor : ourlibraryManagementSystemService.getAccounts()) {
            logger.info(accountDescriptor.getPerson().getName());
        }


        //Now use the client interface
        String eMailTudor = accountDescriptorTB.getPerson().getEMail();
        String passwordTudor = accountDescriptorTB.getPassword();
        MembershipState membershipStateTudor = accountDescriptorTB.getMembershipState();

        //log in to the account
        libraryManagementSystemService.login(eMailTudor, passwordTudor);
        Token tokenTudor = accountDescriptorTB.getToken();

        logger.info("Token of account Tudor Busuioc = " + accountDescriptorTB.getToken());
        logger.info("Tudor's eMail: " + eMailTudor);
        logger.info("Tudor's password: " + passwordTudor);
        logger.info("Tudor's membership state: " + membershipStateTudor);
        logger.info("Tudor's account token: " + tokenTudor);

        libraryManagementSystemService.login(accountDescriptorSG.getPerson().getEMail(), accountDescriptorSG.getPassword());
        logger.info("Token of account Soeren Greiner = " + accountDescriptorSG.getToken());

        libraryManagementSystemService.login(accountDescriptorJW.getPerson().getEMail(), accountDescriptorJW.getPassword());
        logger.info("Token of account Joerg Winckler = " + accountDescriptorJW.getToken());

        libraryManagementSystemService.login(accountDescriptorFK.getPerson().getEMail(), accountDescriptorFK.getPassword());
        logger.info("Token of account Friedrich Karrle = " + accountDescriptorFK.getToken());

        libraryManagementSystemService.login(accountDescriptorMA.getPerson().getEMail(), accountDescriptorMA.getPassword());
        logger.info("Token of account Michael Abel = " + accountDescriptorMA.getToken());

        libraryManagementSystemService.login(accountDescriptorRB.getPerson().getEMail(), accountDescriptorRB.getPassword());
        logger.info("Token of account Rebecca Biebl = " + accountDescriptorRB.getToken());

        libraryManagementSystemService.login(accountDescriptorFD.getPerson().getEMail(), accountDescriptorFD.getPassword());
        logger.info("Token of account Felix Donath = " + accountDescriptorFD.getToken());

        libraryManagementSystemService.login(accountDescriptorVF.getPerson().getEMail(), accountDescriptorVF.getPassword());
        logger.info("Token of account Virginia Feldmann = " + accountDescriptorVF.getToken());


        logger.info("" + accountDescriptorTB);
        accountDescriptorTB = libraryManagementSystemService.getAccount(tokenTudor);
        logger.info("" + accountDescriptorTB);

        //Borrow some books
        libraryManagementSystemService.borrowBook("9781292208794", tokenTudor);
        libraryManagementSystemService.borrowBook("9781292282503", tokenTudor);
        libraryManagementSystemService.borrowBook("9781292220239", tokenTudor);
        libraryManagementSystemService.borrowBook("9781292241623", tokenTudor);

        logger.info("--> Borrow date: {}", ourlibraryManagementSystemService.getBook("9781292208794").getBookItem().getBorrowDate() );
        logger.info("--> Due date: {}", ourlibraryManagementSystemService.getBook("9781292208794").getBookItem().getDueDate() );


        logger.info("--> Borrowed books:");
        accountDescriptorTB = libraryManagementSystemService.getAccount(tokenTudor);
        logger.info("" + accountDescriptorTB.getBorrowedBooks());

        //Lose some books
        ourlibraryManagementSystemService.bookLost("9781292220239", accountDescriptorTB.getToken());

        //Sets the current date
       // ourlibraryManagementSystemService.setCurrentDate(LocalDate.of(2021, 7, 17));

        //Cancels membership
        logger.info("Cancels the membership:");
        ourlibraryManagementSystemService.cancelMembership(accountDescriptorRB.getPerson().getEMail());
        logger.info(accountDescriptorRB.getMembershipState().toString());

        //Removes account
        logger.info("Removes account: ");
        ourlibraryManagementSystemService.removeAccount(accountDescriptorJW.getPerson().getEMail());

        //Return some books
        adminLibraryManagementSystemService.bookReturned("9781292208794", tudorBusuioc.getEMail());
        adminLibraryManagementSystemService.bookReturned("9781292282503", tudorBusuioc.getEMail());

        //Delete Account
        logger.info("Deletes Account:");
        libraryManagementSystemService.deleteAccount(accountDescriptorVF.getToken());

        Thread.sleep(2000);

        logger.info("--> Check again if account is online");
        accountDescriptorTB = libraryManagementSystemService.getAccount(tokenTudor);
        logger.info("" + accountDescriptorTB);

        Thread.sleep(1000);

        logger.debug("--> Logout of the account");
        libraryManagementSystemService.loginOut(tokenTudor);
        logger.debug("" + accountDescriptorTB.getToken());

        Thread.sleep(2000);

        logger.info("--> Check if Account is Offline");
        logger.info("" + accountDescriptorTB.getToken());
    }
}
