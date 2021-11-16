package server.use_cases;

import server.entities.User;
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
    public int createUser(String firstName, String lastName, String passwordHash, String currentAddress, String currentCity, String email) {
        currentMaxId++;
        int id = currentMaxId;
        users.add(new DummyUserRepositoryEntity(id, firstName, lastName, currentAddress, currentCity, passwordHash, email));
        return id;
    }

    @Override
    public User fetchUser(int userId) throws UserNotFoundException {
        DummyUserRepositoryEntity dbUser = users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);

        if (userId >= 0 && userId <= currentMaxId && dbUser != null) {
            // TODO factory pattern
            User user = new User(dbUser.getFirstName(), dbUser.getLastName(), dbUser.getCurrentAddress(),
                    dbUser.getCurrentCity(), dbUser.getPasswordHash(), dbUser.getContactInfo().getEmail()) {};
            user.getContactInfo().setPhoneNumber(dbUser.getContactInfo().getPhoneNumber());
            user.getContactInfo().setInstagram(dbUser.getContactInfo().getInstagram());
            user.getContactInfo().setFacebook(dbUser.getContactInfo().getFacebook());
            user.getContactInfo().setEmail(dbUser.getContactInfo().getEmail());
            user.setBiography(dbUser.getBiography());
            user.setId(dbUser.getId());
            return user;
        } else {
            throw new UserNotFoundException("User with ID: " + userId + " not found.");
        }
    }

    @Override
    public boolean validateCredentials(String email, String password) {
        for (DummyUserRepositoryEntity user : users) {
            if (user.getContactInfo().getEmail().equals(email) && user.getPasswordHash().equals(password)) {
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
                    dbUser.getCurrentCity(), dbUser.getPasswordHash(), dbUser.getContactInfo().getEmail()) {};
            user.getContactInfo().setPhoneNumber(dbUser.getContactInfo().getPhoneNumber());
            user.getContactInfo().setInstagram(dbUser.getContactInfo().getInstagram());
            user.getContactInfo().setFacebook(dbUser.getContactInfo().getFacebook());
            user.getContactInfo().setEmail(dbUser.getContactInfo().getEmail());
            user.setBiography(dbUser.getBiography());
            user.setId(dbUser.getId());
            users.add(user);
        }

        return users;
    }

    @Override
    public boolean editUserAccount(int userId, String newFirstName, String newLastName, String newAddress, String newCity, String newPassword, String newEmail) {
        DummyUserRepositoryEntity dbUser = users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);

        if (userId >= 0 && userId <= currentMaxId && dbUser != null) {
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

        if (userId >= 0 && userId <= currentMaxId && dbUser != null) {
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
    public List<Integer> fetchUserPets(int userId) throws UserNotFoundException {
        DummyUserRepositoryEntity dbUser = users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);

        if (dbUser == null) {
            throw new UserNotFoundException("User with ID: " + userId + " not found.");
        };

        return dbUser.getPets();
    }

    public void addPet(int userId, int petId) {
        DummyUserRepositoryEntity dbUser = users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);

        if (userId >= 0 && userId <= currentMaxId && dbUser != null) {
            dbUser.getPets().add(petId);
        }
    }
  
}
