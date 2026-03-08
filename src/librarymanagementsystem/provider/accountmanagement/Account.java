package de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement;

import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalParameterException;
import de.hhn.it.pp.components.librarymanagementsystem.AccountDescriptor;
import de.hhn.it.pp.components.librarymanagementsystem.AccountListener;

public interface Account {

    void login() throws IllegalParameterException, IllegalStateException;

    void loginOut() throws IllegalParameterException, IllegalStateException;

    void borrowBook() throws IllegalParameterException;

    void returnBook() throws IllegalParameterException;

    void notifyMember() throws IllegalParameterException;

    AccountDescriptor getDescriptor();
}
