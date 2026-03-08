package de.hhn.it.pp.javafx.controllers.libraryManagementSystem;

import de.hhn.it.pp.components.librarymanagementsystem.AccountDescriptor;
import de.hhn.it.pp.components.librarymanagementsystem.LibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.AccountNotExistException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalParameterException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalStateException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NoAgreementException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NullPointerException;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.BookItem;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class BorrowedBookScreenController extends AnchorPane {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(BorrowedBookScreenController.class);

    @FXML
    AnchorPane controlAnchorPane;
    @FXML
    Label borrowedBooksLabel;
    @FXML
    Label currentDateLabel;
    @FXML
    Button extendButton;
    @FXML
    Button lostButton;
    @FXML
    TableView<Book> tableView;
    @FXML
    TableColumn<Book, String> isbnColumn;
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
    @FXML
    TableColumn<Book, LocalDate> dueDateColumn;
    @FXML
    TableColumn<BookItem, BookItemState> bookItemStatusColumn;

    Node actualControlAnchorPaneNode;

    private LibraryManagementSystemService libraryManagementSystemService;
    private AccountDescriptor accountDescriptor;
    List<Book> currentBorrowedBooks;
    ObservableList<Book> observableBorrowedBooks;
    String chosenBookIsbn;

    public BorrowedBookScreenController(LibraryManagementSystemService libraryManagementSystemService, AccountDescriptor accountDescriptor, List<Book> borrowedBooks) throws IllegalParameterException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/libraryManagementSystem"
                + "/BorrowedBooksScreen.fxml"));

        observableBorrowedBooks = FXCollections.observableArrayList();

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String dateToStr = libraryManagementSystemService.getLocalDate().format(formatter);
        currentDateLabel.setText("Current Date: " + dateToStr);
        this.libraryManagementSystemService = libraryManagementSystemService;
        this.accountDescriptor = accountDescriptor;
        this.currentBorrowedBooks = borrowedBooks;

        for (Book book : currentBorrowedBooks) {
            observableBorrowedBooks.add(book);
        }

        dueDateColumn.setCellValueFactory(new PropertyValueFactory<Book, LocalDate>("dueDate"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("subject"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("language"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("numberOfPages"));

        tableView.setItems(observableBorrowedBooks);
    }

    @FXML
    public void handleMouseClick(MouseEvent arg0) {
        logger.debug("clicked on " + tableView.getSelectionModel().getSelectedItem());

        chosenBookIsbn = tableView.getSelectionModel().getSelectedItem().getIsbn();
    }

    public void onExtend(ActionEvent actionEvent) {
        logger.debug("Extend on pressed");
        try {
            libraryManagementSystemService.extendBorrowingBook(chosenBookIsbn, accountDescriptor.getToken());
            successInformation("You have successfully extended the borrow period of the book: " + libraryManagementSystemService.getBook(chosenBookIsbn).getTitle());
            BorrowedBookScreenController activeBorrowedBookScreenController =
                    new BorrowedBookScreenController(libraryManagementSystemService, accountDescriptor, accountDescriptor.getBorrowedBooks());
            actualControlAnchorPaneNode = activeBorrowedBookScreenController;
            controlAnchorPane.getChildren().setAll(activeBorrowedBookScreenController);
        } catch (NullPointerException e) {
            raiseExceptionToUI(e, "Sorry, you haven't selected a book");
        } catch (NoAgreementException e) {
            raiseExceptionToUI(e, "Sorry your period between due date and borrow date is to big pleas try again a few days later");
        }
        catch (IllegalParameterException | IllegalStateException | AccountNotExistException e) {
            e.printStackTrace();
        }
    }

    public void onLost(ActionEvent actionEvent) {
        logger.debug("Lost on pressed");
        try {
            libraryManagementSystemService.bookLost(chosenBookIsbn, accountDescriptor.getToken());
            successInformation("You have marked the book as lost!");
            observableBorrowedBooks.remove(libraryManagementSystemService.getBook(chosenBookIsbn));
            libraryManagementSystemService.getAccount(accountDescriptor.getToken()).getBorrowedBooks().remove(libraryManagementSystemService.getBook(chosenBookIsbn));
            libraryManagementSystemService.getBooksToNotify().remove(libraryManagementSystemService.getBook(chosenBookIsbn));
        } catch (NullPointerException e) {
            raiseExceptionToUI(e, "Sorry, you haven't selected a book");
        } catch (IllegalStateException | NoAgreementException | IllegalParameterException e) {
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
