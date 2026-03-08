package de.hhn.it.pp.javafx.controllers.libraryManagementSystem;

import de.hhn.it.pp.components.librarymanagementsystem.AccountDescriptor;
import de.hhn.it.pp.components.librarymanagementsystem.LibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.*;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalStateException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NullPointerException;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.BookItemState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class CatalogScreenController extends AnchorPane {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(CatalogScreenController.class);

    @FXML
    AnchorPane controlAnchorPane;
    @FXML
    Label searchLabel;
    @FXML
    Button borrowButton;
    @FXML
    TableView<Book> tableView;
    @FXML
    TableColumn<Book, String> isbnColumn;
    @FXML
    TableColumn<Book, BookItemState> stateColumn;
    @FXML
    TableColumn<Book, String> titleColumn;
    @FXML
    TableColumn<Book, String> subjectColumn;
    @FXML
    TableColumn<Book, String> publisherColumn;
    @FXML
    TableColumn<Book, String> languageColumn;
    @FXML
    TableColumn<Book, Integer> pagesColumn;

    Node actualControlAnchorPaneNode;

    LibraryManagementSystemService libraryManagementSystemService;
    AccountDescriptor accountDescriptor;
    List<Book> activeFoundBooks;
    ObservableList<Book> observableFoundBooks;
    String chosenBookIsbn;

    public CatalogScreenController(LibraryManagementSystemService libraryManagementSystemService, AccountDescriptor accountDescriptor, List<Book> foundBooks) throws IllegalStateException, NoAgreementException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/libraryManagementSystem"
                + "/CatalogScreen.fxml"));

        observableFoundBooks = FXCollections.observableArrayList();

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.libraryManagementSystemService = libraryManagementSystemService;
        this.accountDescriptor = accountDescriptor;
        this.activeFoundBooks = foundBooks;
        for (Book book : activeFoundBooks) {
            observableFoundBooks.add(book);
        }

        isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<Book, BookItemState>("bookItemState"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("subject"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("language"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("numberOfPages"));

        tableView.setItems(observableFoundBooks);
    }

    @FXML
    public void handleMouseClick(MouseEvent arg0) {
        logger.debug("clicked on " + tableView.getSelectionModel().getSelectedItem());

        chosenBookIsbn = tableView.getSelectionModel().getSelectedItem().getIsbn();
    }

    public void onBorrow(ActionEvent actionEvent) {
        logger.debug("Borrow on pressed");
        try {
            libraryManagementSystemService.borrowBook(chosenBookIsbn, accountDescriptor.getToken());
            successInformation("You have successfully borrowed the book: " + libraryManagementSystemService.getBook(chosenBookIsbn).getTitle());
            CatalogScreenController activeCatalogScreenController =
                    new CatalogScreenController(libraryManagementSystemService, accountDescriptor, libraryManagementSystemService.getFoundBooks());
            actualControlAnchorPaneNode = activeCatalogScreenController;
            controlAnchorPane.getChildren().setAll(activeCatalogScreenController);
        } catch (IllegalStateException e) {
            raiseExceptionToUI(e, "Sorry, the chosen book is currently not available!");
        } catch (NullPointerException e) {
            raiseExceptionToUI(e, "Sorry, you haven't selected a book");
        } catch (NoAgreementException | IllegalParameterException e) {
            e.printStackTrace();
        } catch (AccountNotExistException e) {
            e.printStackTrace();
        }
    }


    private void successInformation(final String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    private void raiseExceptionToUI(final Exception e, final String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
