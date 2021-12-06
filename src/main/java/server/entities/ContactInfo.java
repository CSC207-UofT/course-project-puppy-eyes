package server.entities;

/**
 * A class representing contact information for a User.
 */
public class ContactInfo {

    private String phoneNumber;
    private String email;
    private String instagram;
    private String facebook;

    public ContactInfo() {
        this.phoneNumber = "";
        this.email = "";
        this.instagram = "";
        this.facebook = "";
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String address){
        this.email = address;
    }

    public void setInstagram(String instagram){
        this.instagram = instagram;
    }

    public void setFacebook(String facebook){
        this.facebook = facebook;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getFacebook() {
        return facebook;
    }
}