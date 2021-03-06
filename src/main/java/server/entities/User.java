package server.entities;

/**
 * Represents a User Entity.
 */
public class User {

    private String firstName, lastName, currentAddress, currentCity, password, biography = "", lat = "", lng = "";
    private double matchingDistanceCap;
    private final ContactInfo contactInfo;
    private int id;

    /**
     * Creates a new User given their first name, last name, current address, current city, password, and email.
     *
     * @param firstName      the first name of the user
     * @param lastName       the last name of the user
     * @param currentAddress the current address of the user
     * @param currentCity    the current city of the user
     * @param password       the user's password
     * @param email          the user's email
     */
    public User(String firstName, String lastName, String currentAddress, String currentCity, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentAddress = currentAddress;
        this.currentCity = currentCity;
        this.matchingDistanceCap = 20;
        this.password = password;
        this.contactInfo = new ContactInfo();
        this.contactInfo.setEmail(email);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getCurrentAddress() {
        return this.currentAddress;
    }

    public String getCurrentCity() {
        return this.currentCity;
    }

    public double getMatchingDistanceCap() {
        return this.matchingDistanceCap;
    }

    public String getPasswordHash() {
        return this.password;
    }

    public String getLat() {
        return this.lat;
    }

    public String getLng() {
        return this.lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getBiography() {
        return this.biography;
    }

    public ContactInfo getContactInfo() {
        return this.contactInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setMatchingDistanceCap(double cap) {
        this.matchingDistanceCap = cap;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User))
            return false;

        User other = (User) o;

        return other.getId() == this.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

}