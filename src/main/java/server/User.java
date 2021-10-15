package server;

import java.util.ArrayList;

public abstract class User {
    private String firstName;
    private String lastName;
    private String homeAddress;
    private ArrayList<Pet> petList;
    /* A hash of the user's password. */
    // In a security perspective, it is unwise to store the user's password
    // as raw plaintext.
    private String passwordHash;
    private ContactInfo contactInfo;
    private String biography;

    /**
     * Creates a User, storing their first and last name, home address, password, email, list of owned pets,
     * biography, ContactInfo, and a randomly generated ID.
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

    public void setBiography(String biography){
        this.biography = biography;
    }

    /**
     * An abstract method to add a pet to the user's list of pets.
     * @param pet The pet which is to be added to the user's list of pets.
     */
    public abstract boolean addPet(Pet pet);

}