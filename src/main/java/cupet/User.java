package cupet;

import java.util.ArrayList;
import java.util.Random;

public abstract class User{
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
    private final int id;


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

        // TODO: Generate an ID from the database
        Random id = new Random();
        this.id = Math.abs(id.nextInt());
    }

    /**
     * The getter for the first name of the user.
     */
    public String getFirstName() {return this.firstName;}

    /**
     * The getter for the last name of the user.
     */
    public String getLastName() {return this.lastName;}

    /**
     * The getter for the home address of the user.
     */
    public String getHomeAddress(){
        return this.homeAddress;
    }

    /**
     * The getter for the passwordHash of the user.
     */
    public String getPasswordHash(){
        return this.passwordHash;
    }

    /**
     * The getter for the biography of the user.
     */
    public String getBiography(){
        return this.biography;
    }

    /**
     * The getter for the ContactInfo of the user.
     */
    public ContactInfo getContactInfo(){
        return this.contactInfo;
    }

    /**
     * The getter for the list of pets of the user.
     */
    public ArrayList<Pet> getPetList(){
        return this.petList;
    }

    /**
     * The setter for the biography of the user.
     */
    public boolean setBiography(String biography){
        this.biography = biography;
        return true;
    }
    /**
     * An abstract method to add a pet to the user's list of pets.
     * @param pet The pet which is to be added to the user's list of pets.
     */
    abstract boolean add_pet(Pet pet);

}