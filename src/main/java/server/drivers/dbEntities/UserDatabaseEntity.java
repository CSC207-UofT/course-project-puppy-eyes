package server.drivers.dbEntities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "home_address")
    private String homeAddress;
    @Column(name = "biography")
    private String biography;
    @OneToOne(cascade = CascadeType.ALL)
    private ContactInfoDatabaseEntity contactInfo;

    public UserDatabaseEntity(String firstName, String lastName, String password, String homeAddress,
                              String biography, ContactInfoDatabaseEntity contactInfo) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.homeAddress = homeAddress;
        this.biography = biography;
        this.contactInfo = contactInfo;
    }

    public UserDatabaseEntity() {

    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return firstName;
    }

    public String getPassword() { return password; }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getBiography() {
        return biography;
    }

    public ContactInfoDatabaseEntity getContactInfo() {
        return contactInfo;
    }

}