package de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement;

import de.hhn.it.pp.components.librarymanagementsystem.provider.Fine;

import java.time.LocalDate;

public class BookItem {
    private int price;
    private LocalDate dueDate;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private BookItemState bookItemState;
    private Fine fine;
    private int elapsedDays;

    public BookItem() {
        this.bookItemState = BookItemState.AVAILABLE;
    }

    /**
     * Sets /Changes the price of the book.
     *
     * @param price of the book
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Sets/ changes the status of the book.
     *
     * @param bookItemState status of the book
     */
    public void setBookStatus(BookItemState bookItemState) {
        this.bookItemState = bookItemState;
    }

    /**
     * Returns the price of the book, that must be paid by the borrower in case of loss of the book.
     *
     * @return price of the book
     */
    public int getBookPrice() {
        return this.price;
    }

    /**
     * Returns the status of the book.
     *
     * @return status of the book
     */
    public BookItemState getBookStatus() {
        return this.bookItemState;
    }


    /**
     * Sets the borrow date.
     *
     * @param borrowDate date when the book was borrowed
     */
    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    /**
     * Return the date when the book was borrowed.
     *
     * @return date when the book was borrowed
     */
    public LocalDate getBorrowDate() {
        return this.borrowDate;
    }

    /**
     * Sets the due date chosen by the member/ borrower/ librarian.
     *
     * @param dueDate due date of the book
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns the date when the book must be returned (due date).
     *
     * @return date when the book must be returned
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the date when the member/ borrower/ client has returned the book.
     *
     * @param returnDate return date when the borrower has returned the book
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Returns the date when the member/ borrower/ client has returned the book.
     *
     * @return date when the borrower has returned the book
     */
    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    /**
     * Sets the days after deadline (elapsed days).
     *
     * @param elapsedDays days after deadline
     */
    public void setElapsedDays(int elapsedDays) {
        this.elapsedDays = elapsedDays;
    }

    /**
     * Gets the days after deadline (elapsed days).
     *
     * @return elapsedDays days after deadline
     */
    public int getElapsedDays() {
        return elapsedDays;
    }

    /**
     * Gets the fine of the Book for the user.
     *
     * @return fine for the user
     */
    public Fine getFine() {
        return fine;
    }

    /**
     * Sets the Fine for the book.
     */
    public void setFineForBook() {
        fine = new Fine(elapsedDays);
    }
}
