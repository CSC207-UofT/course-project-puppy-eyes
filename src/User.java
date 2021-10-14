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
    private ContactInfo contactInfo;
    private String biography;

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

    public boolean setBiography(String biography){
        this.biography = biography;
        return true;
    }

    abstract boolean add_pet(Pet pet);

}