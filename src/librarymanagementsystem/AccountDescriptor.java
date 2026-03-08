package de.hhn.it.pp.components.librarymanagementsystem;

import de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement.Person;
import de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement.Book;
import de.hhn.it.pp.components.librarymanagementsystem.provider.tokenFactory.Token;

import java.util.ArrayList;

public class AccountDescriptor {

    private String password;
    private Token token;
    private MembershipState membershipState;
    private Person person;
    private ArrayList<Book> borrowedBooks;

    /**
     * Constructor stating type of the Account.
     */
    public AccountDescriptor(final String password, Person person) {
        this.password = password;
        this.person = person;
        this.membershipState = MembershipState.ACCEPTED;
        borrowedBooks = new ArrayList<>();
    }

    /**
     * Returns password of the borrower/ account.
     *
     * @return password of the borrower / account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets/ Changes password of the borrower/ account.
     *
     * @param password of the borrower/ account
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the membership state.
     *
     * @return membership state
     */
    public MembershipState getMembershipState() {
        return membershipState;
    }

    /**
     * Sets/ Changes the membership state.
     *
     * @param membershipState membership state
     */
    public void setMembershipState(MembershipState membershipState) {
        this.membershipState = membershipState;
    }

    /**
     * Returns the person assigned to the account.
     *
     * @return person assigned to the account
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets changes in person.
     *
     * @param person object to change
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Returns the token assigned to the account.
     *
     * @return token assigned to the account
     */
    public Token getToken() {
        return token;
    }

    /**
     * Sets the token assigned to the account
     *
     * @param token assigned to the account
     */
    public void setToken(final Token token) {
        this.token = token;
    }

    /**
     * Sets a borrowed book to the borrower account.
     *
     * @param book book that gets borrowed
     */
    public void setBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    /**
     * Returns the books loaned by the borrower/ assigned to the account.
     *
     * @return books loaned by the borrower
     */
    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    /**
     * Returns general account information.
     *
     * @return password, state of the account, person assigned to the account, account id and token
     */
    public String toString() {
        return "AccountDescriptor{"
                + "token of account=" + token + '\''
                + "Password of account=" + password + '\''
                + "Person of account=" + person + '\''
                + "Sate of membership account=" + membershipState + '\''
                + '}';
    }
}
