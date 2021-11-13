package server.use_cases;

import server.entities.ContactInfo;
import server.entities.User;
import server.use_cases.repo_abstracts.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A dummy class representing a user entity in the repository
 */
class DummyUserRepositoryEntity{
    private String firstName;
    private String lastName;
    private String currentAddress;
    private String currentCity;
    private String passwordHash;
    private String email;
    private String biography;
    private DummyContactInfoRepositoryEntity contactInfo;

    public DummyUserRepositoryEntity(String firstName, String lastName, String currentAddress, String currentCity, String passwordHash, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentAddress = currentAddress;
        this.currentCity = currentCity;
        this.passwordHash = passwordHash;
        this.contactInfo = new DummyContactInfoRepositoryEntity();
        this.contactInfo.setEmail(email);
        this.biography = "";
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

    public String getEmail() {
        return email;
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

    public void setEmail(String email) {
        this.email = email;
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
    public int createUser(String firstName, String lastName, String currentAddress, String currentCity, String passwordHash, String email) {
        currentMaxId++;
        int id = currentMaxId;
        users.add(new DummyUserRepositoryEntity(firstName, lastName, currentAddress, currentCity, passwordHash, email));

        return id;
    }

    @Override
    public UserRepositoryUserAccountFetcherResponse fetchUserAccount(int userId) throws UserNotFoundException {
        if (userId >= 0 && userId <= currentMaxId){
            DummyUserRepositoryEntity user = users.get(userId);
            return new UserRepositoryUserAccountFetcherResponse(user.getFirstName(), user.getLastName(),
                    user.getCurrentAddress(), user.getCurrentCity(), user.getEmail());
        } else {
            throw new UserNotFoundException("User with ID: " + userId + " not found.");
        }
    }

    @Override
    public boolean validateCredentials(String email, String password) {
        for (DummyUserRepositoryEntity user : users) {
            if (user.getEmail().equals(email) && user.getPasswordHash().equals(password)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<User> fetchAllUsers() {
        List<DummyUserRepositoryEntity> dbUsers = this.users;
        List<User> users = new ArrayList<>();

        // TODO factory pattern
        for (DummyUserRepositoryEntity dbUser : dbUsers) {
            User user = new User(dbUser.getFirstName(), dbUser.getLastName(), dbUser.getCurrentAddress(),
                    dbUser.getCurrentCity(), dbUser.getPasswordHash(), dbUser.getEmail()) {};
            users.add(user);
        }

        return users;
    }

    @Override
    public boolean editUserAccount(int userId, String newFirstName, String newLastName, String newAddress, String newCity, String newPassword, String newEmail) {
        if (userId >= 0 && userId <= currentMaxId) {
            DummyUserRepositoryEntity user = users.get(userId);
            user.setFirstName(newFirstName);
            user.setLastName(newLastName);
            user.setCurrentAddress(newAddress);
            user.setCurrentCity(newCity);
            user.setPasswordHash(newPassword);
            user.setEmail(newEmail);
            return true;
        } else return false;
    }


    @Override
    public UserRepositoryUserProfileFetcherResponse fetchUserProfile(int userId) throws UserNotFoundException {
        if (userId >= 0 && userId <= currentMaxId){
            DummyUserRepositoryEntity user = users.get(userId);
            DummyContactInfoRepositoryEntity contactInfo = user.getContactInfo();
            return new UserRepositoryUserProfileFetcherResponse(user.getFirstName(), user.getLastName(),
                    user.getBiography(), contactInfo.getPhoneNumber(), contactInfo.getEmail(),
                    contactInfo.getInstagram(), contactInfo.getFacebook());
        } else {
            throw new UserNotFoundException("User with ID: " + userId + " not found.");
        }
    }


    @Override
    public boolean editUserProfile(int userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook) {
        if (userId >= 0 && userId <= currentMaxId) {
            DummyUserRepositoryEntity user = users.get(userId);
            DummyContactInfoRepositoryEntity contactInfo = user.getContactInfo();
            user.setBiography(newBiography);
            contactInfo.setPhoneNumber(newPhoneNumber);
            contactInfo.setInstagram(newInstagram);
            contactInfo.setFacebook(newFacebook);
            return true;
        } else return false;
    }

}
