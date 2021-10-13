import java.util.ArrayList;

public abstract class User{
    private String name;
    private String homeAddress;
    private ArrayList<Pet> petList;
    private String password;
    private int reports;
    private ContactInfo contact;
    private String biography;

    public User(String name, String home, String pass, String email){
        this.name = name;
        this.homeAddress = home;
        this.password = pass;
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

    public String getUserPassword(){
        return this.password;
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