package de.hhn.it.pp.javafx.controllers.libraryManagementSystem;

import de.hhn.it.pp.components.librarymanagementsystem.AccountDescriptor;
import de.hhn.it.pp.components.librarymanagementsystem.LibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.*;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalStateException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NullPointerException;
import de.hhn.it.pp.components.librarymanagementsystem.provider.AdminLibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.provider.OurLibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Address;
import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Person;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;
import de.hhn.it.pp.components.librarymanagementsystem.provider.tokenFactory.Token;
import de.hhn.it.pp.javafx.controllers.Controller;
import de.hhn.it.pp.javafx.controllers.LibraryManagementSystemServiceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LibraryManagementSystemController extends AnchorPane {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(LibraryManagementSystemController.class);

    @FXML
    AnchorPane mainPane;
    @FXML
    AnchorPane controlAnchorPane;
    @FXML
    AnchorPane navigationPanelPane;
    @FXML
    TextField searchField;
    @FXML
    Button searchButton;
    @FXML
    Button homeButton;
    @FXML
    Button borrowedBookButton;
    @FXML
    Button logoutButton;

    Node actualControlAnchorPaneNode;

    public LibraryManagementSystemService libraryManagementSystemService;
    public AccountDescriptor accountDescriptor;

    public LibraryManagementSystemController(final LibraryManagementSystemService libraryManagementSystemService,
                                             final AccountDescriptor accountDescriptor) throws IllegalParameterException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/libraryManagementSystem"
                + "/LibraryManagementSystem.fxml"));

        loader.setRoot(this);
        loader.setController(this);


        try {
            loader.load();
            HomeScreenController activeHomeScreenController =
                    new HomeScreenController(libraryManagementSystemService, accountDescriptor, libraryManagementSystemService.getBooksToNotify());
            actualControlAnchorPaneNode = activeHomeScreenController;
            controlAnchorPane.getChildren().setAll(activeHomeScreenController);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.accountDescriptor = accountDescriptor;
        this.libraryManagementSystemService = libraryManagementSystemService;
        searchField.setOnKeyPressed(
                event -> {
                    switch (event.getCode()){
                        case ENTER: onSearch();
                    }
                }
        );
        checkForWarning();
    }

    public void onSearch() {
        logger.debug("Search catalog on pressed.");
        try {
            if (searchField != null) {
                libraryManagementSystemService.search(searchField.getText());
                CatalogScreenController activeCatalogScreenController =
                        new CatalogScreenController(libraryManagementSystemService, accountDescriptor, libraryManagementSystemService.getFoundBooks());
                actualControlAnchorPaneNode = activeCatalogScreenController;
                controlAnchorPane.getChildren().setAll(activeCatalogScreenController);
            }
        } catch (IllegalParameterException e) {
            raiseExceptionToUI(e, "Search field can't be empty");
        } catch (IllegalStateException | NullPointerException e) {
            e.printStackTrace();
        } catch (NoAgreementException e) {
            e.printStackTrace();
        }
    }

    public void onHome() throws IllegalParameterException, NoAgreementException {
        logger.debug("Home on pressed.");
        controlAnchorPane.getChildren().remove(actualControlAnchorPaneNode);
        HomeScreenController activeHomeScreenController =
                new HomeScreenController(libraryManagementSystemService, accountDescriptor, libraryManagementSystemService.getBooksToNotify());
        actualControlAnchorPaneNode = activeHomeScreenController;
        controlAnchorPane.getChildren().setAll(activeHomeScreenController);
    }

    public void onBorrowedBooks() throws IllegalParameterException {
        logger.debug("Borrowed books on pressed.");
        controlAnchorPane.getChildren().remove(actualControlAnchorPaneNode);
        BorrowedBookScreenController activeBorrowedBookScreenController =
                new BorrowedBookScreenController(libraryManagementSystemService, accountDescriptor, accountDescriptor.getBorrowedBooks());
        actualControlAnchorPaneNode = activeBorrowedBookScreenController;
        controlAnchorPane.getChildren().setAll(activeBorrowedBookScreenController);
    }

    public void onLogout() throws IllegalParameterException, AccountNotExistException {
        logger.debug("Logout on pressed.");
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Do you really want to exit the library management system?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                libraryManagementSystemService.loginOut(accountDescriptor.getToken());
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml"
                        + "/LibraryManagementSystemService.fxml"));
                mainPane.getChildren().setAll(pane);
            } else {
                return;
            }
        } catch (IllegalParameterException | NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }

    public void checkForWarning() throws IllegalParameterException {
            for (Book book : libraryManagementSystemService.getBooksToNotify()) {
                if (Period.between(libraryManagementSystemService.getLocalDate(), book.getBookItem().getDueDate()).getDays() <= 0) {
                    raiseWarningToUi( "Title: " + book.getTitle() + " / Elapsed days: " + book.getBookItem().getElapsedDays());
                }
            }
    }

    private void raiseWarningToUi(final String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Attention: The following book is overdue!!!! Before you continue, please be aware of ths information.");
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void raiseExceptionToUI(final Exception e, final String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
