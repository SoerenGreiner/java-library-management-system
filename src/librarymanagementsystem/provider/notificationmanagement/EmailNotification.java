package de.hhn.it.pp.components.librarymanagementsystem.provider.notificationmanagement;

import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Person;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;

public class EmailNotification extends Notification {

    public EmailNotification(Book book, Person person) {
        super(book, person);
    }
}
