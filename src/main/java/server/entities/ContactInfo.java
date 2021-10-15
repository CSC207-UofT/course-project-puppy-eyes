package server.entities;

public class ContactInfo {
    private String phoneNumber;
    private String email;
    private String instagram;
    private String facebook;

    public ContactInfo(){
        this.phoneNumber = "";
        this.email = "";
        this.instagram = "";
        this.facebook = "";
    }
    /** Probably can set return statement as string or boolean to confirm changes */

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

    // Getter functions
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