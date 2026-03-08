package de.hhn.it.pp.javafx.controllers.libraryManagementSystem;

import de.hhn.it.pp.components.librarymanagementsystem.AccountDescriptor;
import de.hhn.it.pp.components.librarymanagementsystem.LibraryManagementSystemService;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalParameterException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NoAgreementException;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.Period;
import java.util.List;

public class HomeScreenController extends AnchorPane {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(HomeScreenController.class);


    @FXML
    Label titleLabel;
    @FXML
    Label informationLabel;
    @FXML
    Label notificationLabel;
    @FXML
    TableView<Book> tableView;
    @FXML
    TableColumn<Book, Integer> elapsedDaysColumn;
    @FXML
    TableColumn<Book, String> isbnColumn;
    @FXML
    TableColumn<Book, String> titleColumn;
    @FXML
    TableColumn<String, String> labelColumn;


    LibraryManagementSystemService libraryManagementSystemService;
    AccountDescriptor accountDescriptor;
    List<Book> activeBooksToNotify;
    ObservableList<Book> observableBooksToNotify;
    boolean memberWasNotified = false;

    public HomeScreenController(LibraryManagementSystemService libraryManagementSystemService, AccountDescriptor accountDescriptor, List<Book> booksToNotify)  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/libraryManagementSystem"
                + "/HomeScreen.fxml"));

        observableBooksToNotify = FXCollections.observableArrayList();

        loader.setRoot(this);
        loader.setController(this);


        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.libraryManagementSystemService = libraryManagementSystemService;
        this.accountDescriptor = accountDescriptor;
        this.activeBooksToNotify = booksToNotify;

        for (Book book : activeBooksToNotify) {
            observableBooksToNotify.add(book);
        }

        elapsedDaysColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("elapsedDays"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));

        tableView.setItems(observableBooksToNotify);
    }
}
