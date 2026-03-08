package de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement;

public class Person {

    private String name;
    private Address address;
    private String phoneNumber;
    private String eMail;

    public Person(String name, String phoneNumber, String eMail, Address address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.address = address;
    }

    /**
     * Returns the name of the person.
     *
     * @return name of the person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the person
     *
     * @param name name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the address off the person.
     *
     * @return address of the person
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * Returns the phone number of the person.
     *
     * @return phone number of the person
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Sets the phone number to the person.
     *
     * @param phoneNumber phone number of the person
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the email of the person.
     *
     * @return email of the person
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * Sets the email to the person.
     *
     * @param eMail eMail of the person
     */
    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String toString() {
        return "Person{"
                + "Name of person=" + name + '\''
                + "Phone number of person=" + phoneNumber + '\''
                + "E-Mail of person=" + eMail + '\''
                + "Address of person=" + address + '\''
                + '}';
    }
}
