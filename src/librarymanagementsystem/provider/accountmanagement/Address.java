package de.hhn.it.pp.components.librarymanagementsystem.provider.accountmanagement;

public class Address {
    private String streetAddress;
    private String city;
    private String state;



    private String zipCode;
    private String country;

    public Address(String streetAddress, String city, String state, String zipCode, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    /**
     * Returns the street address.
     *
     * @return street address
     */
    public String getStreetAddress() {
        return this.streetAddress;
    }

    /**
     * Sets street address of address
     *
     * @param streetAddress of address
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Returns the city.
     *
     * @return city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Sets city of address
     *
     * @param city of address
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the state.
     *
     * @return state
     */
    public String getState() {
        return this.state;
    }

    /**
     * Sets state of address
     *
     * @param state of address
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns the zip code.
     *
     * @return zip code
     */
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * Sets zip code of address
     *
     * @param zipCode zip code of address
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Returns the country.
     *
     * @return country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets the country of address
     *
     * @param country of address
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public String toString() {
        return "Address{"
                + "Street of address=" + streetAddress + '\''
                + "City of address=" + city + '\''
                + "State of address=" + state + '\''
                + "Zip code of address=" + zipCode + '\''
                + "Country of address=" + country + '\''
                + '}';
    }
}
