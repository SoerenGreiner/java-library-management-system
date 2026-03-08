package de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement;


import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalParameterException;
import de.hhn.it.pp.components.librarymanagementsystem.AccountDescriptor;
import de.hhn.it.pp.components.librarymanagementsystem.AccountListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class OurAccount implements Account {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(OurAccount.class);

    private static AtomicInteger idCounter = new AtomicInteger();
    private List<AccountListener> listeners;
    private AccountDescriptor descriptor;

    /**
     * Constructor to create a Account based on the information in the given AccountDescriptor.
     *
     * @param descriptor descriptor with basic facts about the Account to be created
     */
    public OurAccount(AccountDescriptor descriptor) {
        logger.debug("Constructor - {}", descriptor);
        listeners = new ArrayList<>();
        this.descriptor = descriptor;

        updateDescriptor();
    }

    public static int getIdCounter() {
        return idCounter.get();
    }

    public static void resetIdCounter() {
        OurAccount.idCounter.set(0);
    }

    private void updateDescriptor() {

    }

    @Override
    public void login() throws IllegalParameterException, IllegalStateException {

    }

    @Override
    public void loginOut() throws IllegalParameterException, IllegalStateException {

    }

    @Override
    public void borrowBook() throws IllegalParameterException {

    }

    @Override
    public void returnBook() throws IllegalParameterException {

    }

    @Override
    public void notifyMember() throws IllegalParameterException {

    }

    @Override
    public AccountDescriptor getDescriptor() {
        return descriptor;
    }


}
