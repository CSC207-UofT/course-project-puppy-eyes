package server.entities;

import java.util.ArrayList;

public abstract class User {
    private String firstName;
    private String lastName;
    private String currentAddress;
    private String currentCity;
    private double matchingDistanceCap;
    private final ArrayList<Pet> petList;
    /* A hash of the user's password. */
    // In a security perspective, it is unwise to store the user's password
    // as raw plaintext.
    private String passwordHash;
    private final ContactInfo contactInfo;
    private String biography;
    private int id;

    /**
     * Creates a new User given their first name, last name, current address, current city, password, and email.
     *
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param currentAddress The current address of the user
     * @param currentCity The current city of the user
     * @param password The user's password
     * @param email The user's email
     */
    public User(String firstName, String lastName, String currentAddress, String currentCity, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentAddress = currentAddress;
        this.currentCity = currentCity;
        this.matchingDistanceCap = 20;
        // TODO: Convert the password into a hash.
        // For now, store the passwordHash as the password in a raw format
        this.passwordHash = password;
        this.petList = new ArrayList<>();
        this.contactInfo = new ContactInfo();
        this.contactInfo.setEmail(email);
        this.biography = "";
    }

    public String getFirstName() {return this.firstName;}

    public String getLastName() {return this.lastName;}

    public String getCurrentAddress(){
        return this.currentAddress;
    }

    public String getCurrentCity() {
        return this.currentCity;
    }

    public double getMatchingDistanceCap() {
        return this.matchingDistanceCap;
    }

    public String getPasswordHash(){
        return this.passwordHash;
    }

    public String getBiography(){
        return this.biography;
    }

    public ContactInfo getContactInfo(){
        return this.contactInfo;
    }

    public ArrayList<Pet> getPetList(){
        return this.petList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBiography(String biography){
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
}