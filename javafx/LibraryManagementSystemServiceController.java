package de.hhn.it.pp.javafx.controllers;

import de.hhn.it.pp.components.librarymanagementsystem.exceptions.AccountNotExistException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalParameterException;
import de.hhn.it.pp.components.librarymanagementsystem.AccountDescriptor;
import de.hhn.it.pp.components.librarymanagementsystem.LibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalStateException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NoAgreementException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NullPointerException;
import de.hhn.it.pp.components.librarymanagementsystem.provider.AdminLibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.provider.OurLibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Address;
import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.BookItem;
import de.hhn.it.pp.javafx.controllers.libraryManagementSystem.LibraryManagementSystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LibraryManagementSystemServiceController extends Controller implements Initializable {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(LibraryManagementSystemServiceController.class);

    @FXML
    AnchorPane controlAnchorPane;
    @FXML
    Label loginLabel;
    @FXML
    Button loginButton;
    @FXML
    Button cancelButton;
    @FXML
    TextField mailField;
    @FXML
    PasswordField passwordField;

    Node actualControlAnchorPaneNode;
    private final LibraryManagementSystemService libraryManagementSystemService;
    private final AdminLibraryManagementSystemService adminLibraryManagementSystemService;
    private int firstTimeLogin;


    public LibraryManagementSystemServiceController() throws IllegalParameterException {
        OurLibraryManagementSystemService ourLibraryManagementSystemService = new OurLibraryManagementSystemService();
        AdminLibraryManagementSystemService adminLibraryManagementSystemService = ourLibraryManagementSystemService;
        this.adminLibraryManagementSystemService = adminLibraryManagementSystemService;

        libraryManagementSystemService = ourLibraryManagementSystemService;

            try {
                AccountDescriptor descriptor = new AccountDescriptor("test123",
                        new Person("Test Person", "123456789", "Test@testi.com",
                                new Address("teststreat 123", "testcity", "teststate", "12345", "testcountry")));
                adminLibraryManagementSystemService.addAccount(descriptor);

                Book researchMethods = new Book("Research Methods for Business Students", "Business",
                        "Pearson", "English", "9781292208794", 872, new BookItem());
                adminLibraryManagementSystemService.addBook(researchMethods);

                Book exploringStrategy = new Book("Exploring Strategy", "Business Strategy", "Pearson",
                        "English", "9781292282503", 840, new BookItem());
                adminLibraryManagementSystemService.addBook(exploringStrategy);

                Book principlesOfMarketing = new Book("Principles of Marketing", "Marketing",
                        "Pearson", "English", "9781292220239", 736, new BookItem());
                adminLibraryManagementSystemService.addBook(principlesOfMarketing);

                Book digitalMarketing = new Book("Digital Marketing", "Marketing", "Pearson",
                        "English", "9781292241623", 576, new BookItem());
                adminLibraryManagementSystemService.addBook(digitalMarketing);

                Book accountingFinance = new Book("Accounting and Finance for Non-Specialists", "Accounting",
                        "Pearson", "English", "9781292244051", 616, new BookItem());
                adminLibraryManagementSystemService.addBook(accountingFinance);

                Book operationsManagement = new Book("Operations Management", "Operations", "Pearson",
                        "English", "9781292253992", 776, new BookItem());
                adminLibraryManagementSystemService.addBook(operationsManagement);

                Book logisticsSupplyChainManagement = new Book("Logistics & Supply Chain Management",
                        "Business", "Pearson", "English", "9781292083810", 328, new BookItem());
                adminLibraryManagementSystemService.addBook(logisticsSupplyChainManagement);

                Book lesMiserables = new Book("Les Misérables", "Historical fiction", "Penguin Classics",
                        "English", "9780140444308", 1232, new BookItem());
                adminLibraryManagementSystemService.addBook(lesMiserables);

                Book dieHausaufgabe = new Book("Die Hausaufgabe", "History", "Buchwerkstatt Berlin",
                        "Deutsch", "3946467474", 300, new BookItem());
                adminLibraryManagementSystemService.addBook(dieHausaufgabe);

                Book derAlchimist = new Book("Der Alchimist", "Life and Universe", "Diogenes Verlag, Zürich",
                        "Deutsch", "3257061269", 172, new BookItem());
                adminLibraryManagementSystemService.addBook(derAlchimist);

                libraryManagementSystemService.login(descriptor.getPerson().getEMail(), descriptor.getPassword());
                libraryManagementSystemService.borrowBook(derAlchimist.getIsbn(), descriptor.getToken());
                ourLibraryManagementSystemService.getBook(derAlchimist.getIsbn()).getBookItem().setDueDate(derAlchimist.getBookItem().getDueDate().minusDays(40));
                libraryManagementSystemService.loginOut(descriptor.getToken());
                firstTimeLogin = firstTimeLogin + 1;


            } catch (IllegalParameterException | IllegalStateException | AccountNotExistException | NullPointerException | NoAgreementException e) {
                e.printStackTrace();
            }
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        actualControlAnchorPaneNode = controlAnchorPane;

        mailField.setOnKeyPressed(
                event -> {
                    switch (event.getCode()){
                        case ENTER: onLogin();
                    }
                }
        );

        passwordField.setOnKeyPressed(
                event -> {
                    switch (event.getCode()){
                        case ENTER: onLogin();
                    }
                }
        );
    }

    public void onLogin() {
        logger.debug("Login on pressed.");
        try {
            libraryManagementSystemService.login(mailField.getText(), passwordField.getText());
            LibraryManagementSystemController activeLibraryManagementSystemController =
                    new LibraryManagementSystemController(libraryManagementSystemService, adminLibraryManagementSystemService.getAccountByAdmin(mailField.getText()));
            actualControlAnchorPaneNode = activeLibraryManagementSystemController;
            controlAnchorPane.getChildren().setAll(activeLibraryManagementSystemController);
        }
        catch (IllegalParameterException e) {
            raiseExceptionToUI(e, "Field can't be empty please try again!");
        }
        catch ( AccountNotExistException e) {
            raiseExceptionToUI(e, "Account with mail: " + mailField.getText() + " doesn't exist");
        }
        catch (NoAgreementException e) {
            raiseExceptionToUI(e,"Wrong password please try again!");
        }
        catch (NullPointerException e) {
            raiseExceptionToUI(e, "Parameter can't be null!");
        }
    }

    public void onCancel() {
        logger.debug("Cancel on pressed.");
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Root.fxml"));
            controlAnchorPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void raiseExceptionToUI(final Exception e, final String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}