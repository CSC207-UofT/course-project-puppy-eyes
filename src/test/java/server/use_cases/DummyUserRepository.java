package server.use_cases;

import server.entities.User;
import server.entities.UserBuilder;
import server.use_cases.repo_abstracts.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A dummy class representing a user entity in the repository
 */
class DummyUserRepositoryEntity {

    private int id;
    private String firstName;
    private String lastName;
    private String currentAddress;
    private String currentCity;
    private String passwordHash;
    private String biography;
    private List<Integer> petList;
    private DummyContactInfoRepositoryEntity contactInfo;

    public DummyUserRepositoryEntity(int id, String firstName, String lastName, String currentAddress, String currentCity, String passwordHash, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentAddress = currentAddress;
        this.currentCity = currentCity;
        this.passwordHash = passwordHash;
        this.contactInfo = new DummyContactInfoRepositoryEntity();
        this.contactInfo.setEmail(email);
        this.biography = "";
        this.petList = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public List<Integer> getPets() {
        return this.petList;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getBiography() {
        return biography;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public DummyContactInfoRepositoryEntity getContactInfo() {
        return contactInfo;
    }
}

/**
 * A dummy class representing a contactInfo entity in the repository
 */
class DummyContactInfoRepositoryEntity {
    private String phoneNumber;
    private String email;
    private String instagram;
    private String facebook;

    public DummyContactInfoRepositoryEntity() {
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

/**
 * A dummy user repository that stores users in memory.
 */
public class DummyUserRepository implements IUserRepository {

    private final ArrayList<DummyUserRepositoryEntity> users;
    private int currentMaxId;

    public DummyUserRepository() {
        users = new ArrayList<>();
        currentMaxId = -1;
    }

    @Override
    public int createUser(User user) {
        currentMaxId++;
        int id = currentMaxId;
        users.add(new DummyUserRepositoryEntity(id, user.getFirstName(), user.getLastName(), user.getCurrentAddress(),
                user.getCurrentCity(), user.getPasswordHash(), user.getContactInfo().getEmail()));
        return id;
    }

    @Override
    public User fetchUser(int userId) {
        DummyUserRepositoryEntity dbUser = users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);

        if (dbUser != null) {
            User user = new UserBuilder(
                dbUser.getFirstName(),
                dbUser.getLastName(),
                dbUser.getPasswordHash(),
                dbUser.getCurrentCity(),
                dbUser.getContactInfo().getEmail()
            )
            .currentAddress(dbUser.getCurrentAddress())
            .biography(dbUser.getBiography())
            .phoneNumber(dbUser.getContactInfo().getPhoneNumber())
            .instagram(dbUser.getContactInfo().getInstagram())
            .facebook(dbUser.getContactInfo().getFacebook())
            .id(dbUser.getId())
            .create();

            return user;
        } else {
            return null;
        }
    }

    @Override
    public List<User> fetchAllUsers() {
        List<DummyUserRepositoryEntity> dbUsers = this.users;
        List<User> users = new ArrayList<>();

        for (DummyUserRepositoryEntity dbUser : dbUsers) {
            User user = new UserBuilder(
                dbUser.getFirstName(),
                dbUser.getLastName(),
                dbUser.getPasswordHash(),
                dbUser.getCurrentCity(),
                dbUser.getContactInfo().getEmail()
            )
            .currentAddress(dbUser.getCurrentAddress())
            .biography(dbUser.getBiography())
            .phoneNumber(dbUser.getContactInfo().getPhoneNumber())
            .instagram(dbUser.getContactInfo().getInstagram())
            .facebook(dbUser.getContactInfo().getFacebook())
            .id(dbUser.getId())
            .create();
            users.add(user);
        }

        return users;
    }

    @Override
    public boolean editUserAccount(int userId, String newFirstName, String newLastName, String newAddress, String newCity, String newPassword, String newEmail) {
        DummyUserRepositoryEntity dbUser = users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);

        if (dbUser != null) {
            dbUser.setFirstName(newFirstName);
            dbUser.setLastName(newLastName);
            dbUser.setCurrentAddress(newAddress);
            dbUser.setCurrentCity(newCity);
            dbUser.setPasswordHash(newPassword);
            dbUser.getContactInfo().setEmail(newEmail);
            return true;
        } else return false;
    }

    @Override
    public boolean editUserProfile(int userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook) {
        DummyUserRepositoryEntity dbUser = users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);

        if (dbUser != null) {
            DummyContactInfoRepositoryEntity contactInfo = dbUser.getContactInfo();
            dbUser.setBiography(newBiography);
            contactInfo.setPhoneNumber(newPhoneNumber);
            contactInfo.setInstagram(newInstagram);
            contactInfo.setFacebook(newFacebook);
            return true;
        } else return false;
    }

    @Override
    public int fetchIdFromEmail(String email) {
        for (DummyUserRepositoryEntity dbUser : users) {
            if (dbUser.getContactInfo().getEmail().equals(email)) {
                return dbUser.getId();
            }
        }

        return -1;
    }

    @Override
    public List<Integer> fetchUserPets(int userId) {
        DummyUserRepositoryEntity dbUser = users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);

        if (dbUser == null) {
            return null;
        };

        return dbUser.getPets();
    }

    public void addPet(int userId, int petId) {
        DummyUserRepositoryEntity dbUser = users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);

        if (dbUser != null) {
            dbUser.getPets().add(petId);
        }
    }
  
}
