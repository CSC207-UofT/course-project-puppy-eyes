import java.awt.*;
import java.util.ArrayList;

public abstract class User{
    private String name;
    private String address;
    private String biography;
    private ContactInfo contactInfo;
    private ArrayList<Pet> petList;
    private int reports;

    public User(String name, String address, String biography, ContactInfo contact){
        this.name = name;
        this.address = address;
        this.biography = biography;
        this.contactInfo = contact;
        this.reports = 0;
        petList = new ArrayList<Pet>();
    }
    public String getUserName(){
        return this.name;
    }

    public String getUserAddress(){
        return this.address;
    }

    public String getUserBiography(){
        return this.biography;
    }

    public ContactInfo getUserContact(){
        return this.contactInfo;
    }

    public ArrayList<Pet> getPetList(){
        return this.petList;
    }

    public int getReports(){
        return this.reports;
    }

    abstract boolean add_pet(Pet pet);

}