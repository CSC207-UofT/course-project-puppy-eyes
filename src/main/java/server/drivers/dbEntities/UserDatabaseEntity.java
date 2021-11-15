package server.drivers.dbEntities;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "current_address")
    private String currentAddress;

    @Column(name = "current_city")
    private String currentCity;

    @Column(name = "matching_distance_cap")
    private Double matchingDistanceCap;

    @Column(name = "biography")
    private String biography;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactInfoDatabaseEntity contactInfo;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<PetDatabaseEntity> pets;

    public UserDatabaseEntity(String firstName, String lastName, String password, String currentAddress,
                              String currentCity, Double matchingDistanceCap, String biography,
                              ContactInfoDatabaseEntity contactInfo) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.currentAddress = currentAddress;
        this.currentCity = currentCity;
        this.matchingDistanceCap = matchingDistanceCap;
        this.biography = biography;
        this.contactInfo = contactInfo;
    }

    public UserDatabaseEntity() { }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() { return password; }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public List<PetDatabaseEntity> getPets() {
        return this.pets;
    }

    public void addPet(PetDatabaseEntity pet) {
        this.pets.add(pet);
        pet.setUser(this);
    }

    public void removePet(PetDatabaseEntity pet) {
        if (!this.pets.contains(pet)) {
            return;
        }

        this.pets.remove(pet);
        pet.setUser(null);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getMatchingDistanceCap() {
        return matchingDistanceCap;
    }

    public String getBiography() {
        return biography;
    }

    public ContactInfoDatabaseEntity getContactInfo() {
        return contactInfo;
    }

    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public void setCurrentAddress(String newAddress) {
        this.currentAddress = newAddress;
    }

    public void setCurrentCity(String newCity) {
        this.currentCity = newCity;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setContactInfo(ContactInfoDatabaseEntity newContactInfo) {
        this.contactInfo = newContactInfo;
    }
}