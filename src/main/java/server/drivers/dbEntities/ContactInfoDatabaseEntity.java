package server.drivers.dbEntities;

import javax.persistence.*;

@Entity
@Table(name = "contact_info")
public class ContactInfoDatabaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "facebook")
    private String facebook;

    public ContactInfoDatabaseEntity(String phoneNumber, String email, String instagram, String facebook) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.instagram = instagram;
        this.facebook = facebook;
    }

    public ContactInfoDatabaseEntity() {

    }

    public int getId() { return id; }

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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}