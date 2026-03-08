package de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement;

import java.time.LocalDate;

public class Book {

    private String isbn;
    private String title;
    private String subject;
    private String publisher;
    private String language;
    private int numberOfPages;
    private BookItem bookItem;

    public Book(String title, String subject, String publisher, String language, String isbn,
                int numberOfPages, BookItem bookItem) {
        this.title = title;
        this.subject = subject;
        this.publisher = publisher;
        this.language = language;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
        this.bookItem = bookItem;
    }

    /**
     * Returns the International Standard Book Number.
     *
     * @return International Standard Book Number
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Set ISBN of the book
     *
     * @param isbn of the book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Returns the title of the book.
     *
     * @return title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title of the book
     *
     * @param title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the subject of the book.
     *
     * @return subject of the book
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Set subject of the book
     *
     * @param subject of the book
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns the publisher of the book.
     *
     * @return publisher of the book
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Set publisher of the book
     *
     * @param publisher of the book
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Returns the language of the book.
     *
     * @return language of the book
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set the language of the book
     *
     * @param language of the book
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Returns the number of pages
     *
     * @return number of pages
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * Set number of pages of the book
     *
     * @param numberOfPages number of pages of the book
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * Returns the item information about the book
     *
     * @return item information about the book
     */
    public BookItem getBookItem() {
        return bookItem;
    }

    /**
     * Returns the due date for the UI
     *
     * @return due date of the book
     */
    public LocalDate getDueDate() {return getBookItem().getDueDate();}

    /**
     * Returns the state of a book
     *
     * @return state of the book
     */
    public BookItemState getBookItemState() {
        return getBookItem().getBookStatus();
    }

    /**
     * Returns the days the a left to the due date
     *
     * @return days there are left
     */
    public Integer getElapsedDays() {
        return getBookItem().getElapsedDays();
    }
}

