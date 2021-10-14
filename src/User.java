import java.util.ArrayList;

public abstract class User{
    private String name;
    private String homeAddress;
    private ArrayList<Pet> petList
    /* A hash of the user's password. */
    // In a security perspective, it is unwise to store the user's password
    // as raw plaintext.
    private String passwordHash;
    private int reports;
    private ContactInfo contact;
    private String biography;

    public User(String name, String home, String password, String email){
        this.name = name;
        this.homeAddress = home;
        // TODO: Convert the password into a hash.
        // For now, store the passwordHash as the password in a raw format
        this.passwordHash = password;
        this.reports = 0;
        petList = new ArrayList<Pet>();
        this.contact = new ContactInfo();
        contact.setEmail(email);
        this.biography = "";
    }
    public String getUserName(){
        return this.name;
    }

    public String getUserAddress(){
        return this.homeAddress;
    }

    public String getPasswordHash(){
        return this.passwordHash;
    }

    public String getUserBiography(){
        return this.biography;
    }

    public ContactInfo getUserContact(){
        return this.contact;
    }

    public ArrayList<Pet> getPetList(){
        return this.petList;
    }

    public int getReports(){
        return this.reports;
    }

    public boolean setUserBiography(String biography){
        this.biography = biography;
        return true;
    }

    abstract boolean add_pet(Pet pet);

}