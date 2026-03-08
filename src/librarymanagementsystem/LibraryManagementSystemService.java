package de.hhn.it.pp.components.librarymanagementsystem;

import de.hhn.it.pp.components.librarymanagementsystem.exceptions.*;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalStateException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NullPointerException;
import de.hhn.it.pp.components.librarymanagementsystem.provider.tokenFactory.Token;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;

import java.time.LocalDate;
import java.util.List;

/**
 * This LibraryManagementSystemService is an interface to a bunch of accounts, e.g. in an university library.
 */
public interface LibraryManagementSystemService {

    /**
     * Login to the account
     *
     * @param eMail    unique token to verify the user
     * @param password to verify the token with the right password
     * @throws IllegalParameterException if the eMail or password didn't matches up
     * @throws AccountNotExistException  if the account doesn't exist
     */
    void login(String eMail, String password) throws IllegalParameterException, NoAgreementException, NullPointerException, AccountNotExistException;

    /**
     * Login out of the account
     *
     * @param token token assign to the use
     * @throws IllegalParameterException if the token is a null reference
     * @throws AccountNotExistException  if the account doesn't exist
     */
    void loginOut(Token token) throws IllegalParameterException, NullPointerException, AccountNotExistException;

    /**
     * Get the personal account.
     *
     * @return account
     * @throws IllegalParameterException if the token is a null reference
     * @throws AccountNotExistException  if the account doesn't exist
     */
    AccountDescriptor getAccount(Token token) throws IllegalParameterException, NullPointerException, AccountNotExistException;

    /**
     * Deletes the account of the member on his own will.
     *
     * @param token token of the account.
     * @throws IllegalParameterException if the token is a null reference
     * @throws AccountNotExistException  if the account doesn't exist
     */
    void deleteAccount(Token token) throws IllegalParameterException, NullPointerException, AccountNotExistException;

    /**
     * Borrow a book from the library.
     *
     * @param isbn           id of the book
     * @param token          token assigned to the account/ borrower
     * @throws IllegalParameterException if something is wrong
     * @throws IllegalStateException book not available in the library
     * @throws AccountNotExistException  if the account doesn't exist
     */
    void borrowBook(String isbn, Token token) throws IllegalParameterException, IllegalStateException, NoAgreementException, NullPointerException, AccountNotExistException;

    /**
     * Returns the book referenced by the isbn.
     *
     * @param isbn id of the book
     * @return found book
     * @throws IllegalParameterException if the given book id doesn't exist
     * @throws IllegalStateException book not available in the library
     */
    Book getBook(String isbn) throws IllegalParameterException, IllegalStateException, NoAgreementException, NullPointerException;

    /**
     * Returns all books that are found in the library.
     *
     * @return books that are found in the library
     */
    List<Book> getFoundBooks();

    /**
     * Search in the library for books with the given string and adds them to a found book array.
     *
     */
    void search(String string) throws IllegalParameterException, NullPointerException;

    /**
     * Sets the book state (LOST), when the book was lost.
     *
     * @param isbn  id of the book
     * @param token id of the borrower/ client, who lost the book
     * @throws IllegalParameterException if isbn or eMail is a null reference
     * @throws AccountNotExistException  if the account doesn't exist
     */
    void bookLost(String isbn, Token token) throws IllegalParameterException, IllegalStateException, NoAgreementException, NullPointerException, AccountNotExistException;

    /**
     * Extends the borrowing date of the chosen book,
     *
     * @param isbn id of the chosen book that extend the borrowing
     * @param token id of the account what extend the borrowing
     */
    void extendBorrowingBook(String isbn, Token token) throws IllegalParameterException, NoAgreementException, NullPointerException, AccountNotExistException;

    /**
     * Returns the locale date
     *
     * @return the locale date
     */
    LocalDate getLocalDate() throws IllegalParameterException;

    /**
     * Returns a list of borrowed books what have to return in the next time
     *
     * @return list of books what have to return
     */
    List<Book> getBooksToNotify();
}
