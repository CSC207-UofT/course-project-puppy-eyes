package server.entities;

import java.util.ArrayList;

public abstract class User {
    private final String firstName;
    private final String lastName;
    private final String homeAddress;
    private final ArrayList<Pet> petList;
    /* A hash of the user's password. */
    // In a security perspective, it is unwise to store the user's password
    // as raw plaintext.
    private final String passwordHash;
    private final ContactInfo contactInfo;
    private String biography;
    private int id;

    /**
     * Creates a new User given their first name, last name, home address, password, and email.
     * Return
     *
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param homeAddress The address of the user's home
     * @param password The user's password
     * @param email The user's email
     */
    public User(String firstName, String lastName, String homeAddress, String password, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
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

    public String getHomeAddress(){
        return this.homeAddress;
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

    // TODO: Complete remaining getters and setters
}