package server.use_cases;

import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.UserNotFoundException;
import server.use_cases.repo_abstracts.UserRepositoryUserAccountFetcherResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A dummy class representing a user entity in the repository
 */
class DummyUserRepositoryEntity{
    private final String firstName;
    private final String lastName;
    private final String homeAddress;
    private final String passwordHash;
    private final String email;

    public DummyUserRepositoryEntity(String firstName, String lastName, String homeAddress, String passwordHash, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.passwordHash = passwordHash;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomeAddress() {
        return homeAddress;
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
    public int createUser(String firstName, String lastName, String homeAddress, String passwordHash, String email) {
        currentMaxId++;
        int id = currentMaxId;
        users.add(new DummyUserRepositoryEntity(firstName, lastName, homeAddress, passwordHash, email));

        return id;
    }

    @Override
    public UserRepositoryUserAccountFetcherResponse fetchUserAccount(int userId) throws UserNotFoundException {
        if (userId >= 0 && userId <= currentMaxId){
            DummyUserRepositoryEntity user = users.get(userId);
            return new UserRepositoryUserAccountFetcherResponse(user.getFirstName(), user.getLastName(),
                    user.getHomeAddress(), user.getEmail());
        }else{
            throw new UserNotFoundException("User with ID: " + userId + " not found.");
        }
    }
}
