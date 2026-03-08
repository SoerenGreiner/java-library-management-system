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

import static org.junit.jupiter.api.Assertions.*;

public class TestAdminInterfaceBadCases {
    OurLibraryManagementSystemService adminLibraryManagementSystemService;
    Address tb;
    Person tudorBusuioc;
    AccountDescriptor accountDescriptorTB;

    BookItem dH;
    Book dieHausaufgabe;

    @BeforeEach
    void setup() throws IllegalParameterException {

        adminLibraryManagementSystemService = new OurLibraryManagementSystemService();

        tb = new Address("Talheimer Strasse 34", "Heilbronn", "Baden Wurttenberg", "74081", "Germany");
        tudorBusuioc = new Person("Tudor Busuioc", "+494364362554", "tudorbusuioc99@gmail.com", tb);
        accountDescriptorTB = new AccountDescriptor("tudor123qwerty", tudorBusuioc);


        dH =new BookItem();
        dieHausaufgabe = new Book("Die Hausaufgabe", "History", "Buchwerkstatt Berlin",
                "Deutsch", "3946467474", 300, dH);
    }

    @Test
    @DisplayName("Gets an account with a null reference and a not existing e-Mail.")
    public void getAccountByAdminTestExceptionsWithNullAndWrongEmail() throws NullPointerException, AccountNotExistException{
        NullPointerException nullPointerException = assertThrows(NullPointerException.class,
                () -> adminLibraryManagementSystemService.getAccountByAdmin(null));
        AccountNotExistException accountNotExistException = assertThrows(AccountNotExistException.class,
                () -> adminLibraryManagementSystemService.getAccountByAdmin("TestEmail@abgehn.de"));
    }

    @Test
    @DisplayName("Adds and remove an account with null and wrong parameter ")
    public void addAndRemoveAccountWithNullAndWrongParameter() throws IllegalParameterException, NullPointerException, NoAgreementException {
        //Add two times the same account
        adminLibraryManagementSystemService.addAccount(accountDescriptorTB);
        NoAgreementException noAgreementException = assertThrows(NoAgreementException.class,
                () -> adminLibraryManagementSystemService.addAccount(accountDescriptorTB));

        //Add accounts with empty strings and check
        //Descriptor
        accountDescriptorTB.setPassword("");
        //Person
        accountDescriptorTB.getPerson().setEMail("");
        accountDescriptorTB.getPerson().setName("");
        accountDescriptorTB.getPerson().setPhoneNumber("");
        //Address
        accountDescriptorTB.getPerson().getAddress().setStreetAddress("");
        accountDescriptorTB.getPerson().getAddress().setCity("");
        accountDescriptorTB.getPerson().getAddress().setZipCode("");
        accountDescriptorTB.getPerson().getAddress().setCountry("");
        accountDescriptorTB.getPerson().getAddress().setState("");
        assertEquals("", accountDescriptorTB.getPassword());
        assertEquals("", accountDescriptorTB.getPerson().getEMail());
        assertEquals("", accountDescriptorTB.getPerson().getName());
        assertEquals("", accountDescriptorTB.getPerson().getPhoneNumber());
        assertEquals("", accountDescriptorTB.getPerson().getAddress().getStreetAddress());
        assertEquals("", accountDescriptorTB.getPerson().getAddress().getCity());
        assertEquals("", accountDescriptorTB.getPerson().getAddress().getZipCode());
        assertEquals("", accountDescriptorTB.getPerson().getAddress().getCountry());
        assertEquals("", accountDescriptorTB.getPerson().getAddress().getState());
        IllegalParameterException illegalParameterException = assertThrows(IllegalParameterException.class,
                () -> adminLibraryManagementSystemService.addAccount(accountDescriptorTB));

        //Add accounts with null strings
        //Descriptor
        accountDescriptorTB.setPassword(null);
        //Person
        accountDescriptorTB.getPerson().setEMail(null);
        accountDescriptorTB.getPerson().setName(null);
        accountDescriptorTB.getPerson().setPhoneNumber(null);
        //Address
        accountDescriptorTB.getPerson().getAddress().setStreetAddress(null);
        accountDescriptorTB.getPerson().getAddress().setCity(null);
        accountDescriptorTB.getPerson().getAddress().setZipCode(null);
        accountDescriptorTB.getPerson().getAddress().setCountry(null);
        accountDescriptorTB.getPerson().getAddress().setState(null);
        assertNull(accountDescriptorTB.getPassword());
        assertNull(accountDescriptorTB.getPerson().getEMail());
        assertNull(accountDescriptorTB.getPerson().getName());
        assertNull(accountDescriptorTB.getPerson().getPhoneNumber());
        assertNull(accountDescriptorTB.getPerson().getAddress().getStreetAddress());
        assertNull(accountDescriptorTB.getPerson().getAddress().getCity());
        assertNull(accountDescriptorTB.getPerson().getAddress().getZipCode());
        assertNull(accountDescriptorTB.getPerson().getAddress().getCountry());
        assertNull(accountDescriptorTB.getPerson().getAddress().getState());
        NullPointerException nullPointerException1 = assertThrows(NullPointerException.class,
                () -> adminLibraryManagementSystemService.addAccount(accountDescriptorTB));

        //Removing
        NullPointerException nullPointerException2 = assertThrows(NullPointerException.class,
                () -> adminLibraryManagementSystemService.removeAccount(null));
        AccountNotExistException accountNotExistException = assertThrows(AccountNotExistException.class,
                () -> adminLibraryManagementSystemService.removeAccount("TestEmail@abgehn.de"));
    }

    @Test
    @DisplayName("Try to cancel and accept a membership of an account with null and wrong parameter")
    public void acceptAndCancelMembershipWithNullAndWrongParameter() throws NullPointerException, AccountNotExistException {
        //Accept
        NullPointerException nullPointerException1 = assertThrows(NullPointerException.class,
                () -> adminLibraryManagementSystemService.acceptMembership(null));
        AccountNotExistException accountNotExistException1 = assertThrows(AccountNotExistException.class,
                () -> adminLibraryManagementSystemService.acceptMembership("TestEmail@abgehn.de"));
        //Cancel
        NullPointerException nullPointerException3 = assertThrows(NullPointerException.class,
                () -> adminLibraryManagementSystemService.cancelMembership(null));
        AccountNotExistException accountNotExistException2 = assertThrows(AccountNotExistException.class,
                () -> adminLibraryManagementSystemService.cancelMembership("TestEmail@abgehn.de"));
    }

    @Test
    @DisplayName("Try to remove a book from library management system with null reference and what isn't available")
    public void addAndRemoveBookWithNullAndWrongParameter() throws NullPointerException, IllegalParameterException, NoAgreementException, IllegalStateException {
        //Add two times the same book
        adminLibraryManagementSystemService.addBook(dieHausaufgabe);
        NoAgreementException noAgreementException = assertThrows(NoAgreementException.class,
                () -> adminLibraryManagementSystemService.addBook(dieHausaufgabe));

        //Remove with null and state loaned
        NullPointerException nullPointerException1 = assertThrows(NullPointerException.class,
                () -> adminLibraryManagementSystemService.removeBook(null));
        dieHausaufgabe.getBookItem().setBookStatus(BookItemState.LOANED);
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class,
                () -> adminLibraryManagementSystemService.removeBook(dieHausaufgabe.getIsbn()));
        dieHausaufgabe.getBookItem().setBookStatus(BookItemState.AVAILABLE);
        adminLibraryManagementSystemService.removeBook(dieHausaufgabe.getIsbn());

        //Add books with illegal parameter
        dieHausaufgabe.setIsbn("");
        dieHausaufgabe.setTitle("");
        dieHausaufgabe.setPublisher("");
        dieHausaufgabe.setLanguage("");
        dieHausaufgabe.setSubject("");
        dieHausaufgabe.setNumberOfPages(0);
        assertEquals("", dieHausaufgabe.getIsbn());
        assertEquals("", dieHausaufgabe.getTitle());
        assertEquals("", dieHausaufgabe.getPublisher());
        assertEquals("", dieHausaufgabe.getLanguage());
        assertEquals("", dieHausaufgabe.getSubject());
        assertEquals(0, dieHausaufgabe.getNumberOfPages());
        IllegalParameterException illegalParameterException1 = assertThrows(IllegalParameterException.class,
                () -> adminLibraryManagementSystemService.addBook(dieHausaufgabe));

        //Add books with null parameter
        dieHausaufgabe.setIsbn(null);
        dieHausaufgabe.setTitle(null);
        dieHausaufgabe.setPublisher(null);
        dieHausaufgabe.setLanguage(null);
        dieHausaufgabe.setSubject(null);
        assertEquals(null, dieHausaufgabe.getIsbn());
        assertEquals(null, dieHausaufgabe.getTitle());
        assertEquals(null, dieHausaufgabe.getPublisher());
        assertEquals(null, dieHausaufgabe.getLanguage());
        assertEquals(null, dieHausaufgabe.getSubject());
        NullPointerException nullPointerException = assertThrows(NullPointerException.class,
                () -> adminLibraryManagementSystemService.addBook(dieHausaufgabe));
    }

    @Test
    @DisplayName("Notify a member with a null reference for isbn and eMail as well as with a not existing isbn and eMail")
    public void notifyMemberTestExceptionsWithNullReferenceForISBNAndEMailAsWellAsWithNotExistingISBNAndEMail() throws NullPointerException, AccountNotExistException {
        //Try to notify with null token
        NullPointerException nullPointerException = assertThrows(NullPointerException.class,
                () -> adminLibraryManagementSystemService.notifyMember(null));
        //Create fake token, try to notify and check
        Token falseToken = new Token();
        AccountNotExistException accountNotExistException = assertThrows(AccountNotExistException.class,
                () -> adminLibraryManagementSystemService.notifyMember(falseToken));
    }
}
