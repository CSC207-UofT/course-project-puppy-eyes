package server.use_cases;

import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.UserNotFoundException;
import server.use_cases.repo_abstracts.UserRepositoryUserAccountFetcherResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * A dummy class representing a user entity in the repository
 */
class DummyUserRepositoryEntity{
    private final String firstName;
    private final String lastName;
    private final String currentAddress;
    private final String currentCity;
    private final String passwordHash;
    private final String email;

    public DummyUserRepositoryEntity(String firstName, String lastName, String currentAddress, String currentCity, String passwordHash, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentAddress = currentAddress;
        this.currentCity = currentCity;
        this.passwordHash = passwordHash;
        this.email = email;
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
        }else{
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

}
