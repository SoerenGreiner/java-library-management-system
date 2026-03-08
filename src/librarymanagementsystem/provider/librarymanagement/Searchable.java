package de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement;

import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalParameterException;

import java.util.List;

public interface Searchable {

    void searchByTitle(String title) throws IllegalParameterException;

    void searchByPublisher(String pulisher) throws IllegalParameterException;

    void searchBySubject(String subject) throws  IllegalParameterException;

}
