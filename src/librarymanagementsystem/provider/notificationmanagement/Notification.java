package de.hhn.it.pp.components.librarymanagementsystem.provider.notificationmanagement;

import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Person;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.BookItem;

import java.time.LocalDate;

public class Notification {
    private Book book;
    private Person person;
    LocalDate date;

    public Notification(Book book, Person person) {
        this.book = book;
        this.person = person;
        date = LocalDate.now();
    }


    /**
     * Sends a notification to the borrower (3,2,1 days) before deadline.
     */
    public boolean sendNotification() {
        System.out.println("#############################################################################################");
        System.out.println("# Dear " + person.getName() + "                                                " + date + " #");
        System.out.println("#     " + person.getAddress().getStreetAddress() + "                                       #");
        System.out.println("#     " + person.getAddress().getZipCode() + " " + person.getAddress().getCity() + "        #");
        System.out.println("#                                                                                           #");
        System.out.println("# FINAL NOTICE: This is your final notice to return the item/s listed below. You may        #");
        System.out.println("# be blocked from borrowing until this matter is taken care of. Replacement charges         #");
        System.out.println("# are indicated below . This may also include a processing fee for lost items. Please       #");
        System.out.println("# contact the owning library immediately. Thank you.                                        #");
        System.out.println("#                                                                                           #");
        System.out.println("# ------------------------------------------------------------------------------------------#");
        System.out.println("# ISBN: " + book.getIsbn() + "                                                          #");
        System.out.println("# Borrow date: " + book.getBookItem().getBorrowDate() + "                                             #");
        System.out.println("# Due date: " + book.getBookItem().getDueDate() + "                                                   #");
        System.out.println("# Elapsed days: " + book.getBookItem().getElapsedDays() + "                                                                                     #");
        System.out.println("# Fine: " + book.getBookItem().getFine() + "                                                                                          #");
        System.out.println("# ------------------------------------------------------------------------------------------#");
        System.out.println("#                                                                                           #");
        System.out.println("#                                                                                           #");
        System.out.println("#                                                                                           #");
        System.out.println("#                                                                                           #");
        System.out.println("# Thank you for visiting the library                                                        #");
        System.out.println("# with regards                                                                              #");
        System.out.println("# Library Team                                                                              #");
        System.out.println("#############################################################################################");
        return true;
    }
}
