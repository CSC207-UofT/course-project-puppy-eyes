import java.util.ArrayList;

public abstract class User{
    private String firstName;
    private String lastName;
    private String homeAddress;
    private ArrayList<Pet> petList;
    /* A hash of the user's password. */
    // In a security perspective, it is unwise to store the user's password
    // as raw plaintext.
    private String passwordHash;
    private ContactInfo contact;
    private String biography;

    public User(String firstName, String lastName, String homeAddress, String password, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = home;
        // TODO: Convert the password into a hash.
        // For now, store the passwordHash as the password in a raw format
        this.passwordHash = password;
        petList = new ArrayList<Pet>();
        this.contact = new ContactInfo();
        contact.setEmail(email);
        this.biography = "";
    }

    public String getName(){
        return this.name;
    }

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
        return this.contact;
    }

    public ArrayList<Pet> getPetList(){
        return this.petList;
    }

    public boolean setBiography(String biography){
        this.biography = biography;
        return true;
    }

    abstract boolean add_pet(Pet pet);

}