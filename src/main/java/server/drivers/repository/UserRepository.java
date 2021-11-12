package server.drivers.repository;

import org.springframework.stereotype.Repository;
import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.UserNotFoundException;
import server.use_cases.repo_abstracts.UserRepositoryUserAccountFetcherResponse;
import server.drivers.dbEntities.ContactInfoDatabaseEntity;
import server.drivers.dbEntities.UserDatabaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An access point from the program to the "User" table in our database.
 */
@Repository
public class UserRepository implements IUserRepository {

    private final JpaUserRepository repository;

    public UserRepository(JpaUserRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Create and save a new user to the database.
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param currentAddress The user's current address.
     * @param currentCity The user's current city
     * @param password The user's password.
     * @param email The user's email.
     *
     * @return The id of the new user.
     */
    @Override
    public int createUser(String firstName, String lastName, String password, String currentAddress, String currentCity, String email) {
        ContactInfoDatabaseEntity contactInfoDbEntity = new ContactInfoDatabaseEntity("", email, "", "");
        UserDatabaseEntity userDbEntity = new UserDatabaseEntity(firstName, lastName, password, currentAddress, currentCity, 10.0, "", contactInfoDbEntity);
        repository.save(userDbEntity);

        return userDbEntity.getId();
    }

    /**
     * Retrieve a user's account information given their user id.
     *
     * @param userId The user's id.
     * @return An object containing the user's first name, last name, current address, current city, and email.
     * @throws UserNotFoundException if no user with such an id was found
     */
    @Override
    public UserRepositoryUserAccountFetcherResponse fetchUserAccount(int userId) throws UserNotFoundException {
        Optional<UserDatabaseEntity> searchResult = repository.findById(userId);

        if (searchResult.isPresent()) {
            UserDatabaseEntity user = searchResult.get();

            return new UserRepositoryUserAccountFetcherResponse(user.getFirstName(), user.getLastName(),
                    user.getCurrentAddress(), user.getCurrentCity(), user.getContactInfo().getEmail());
        }else{
            throw new UserNotFoundException("User of ID: " + userId + " not found");
        }
    }

    /**
     * Return whether an email-password pair exist as credentials in the database.
     * @param email
     * @param password
     * @return true if credentials exist, false otherwise
     */
    @Override
    public boolean validateCredentials(String email, String password) {
        Optional<UserDatabaseEntity> searchResult = Optional.ofNullable(repository.findByContactInfo_email(email));

        if (searchResult.isPresent()) {
            UserDatabaseEntity user = searchResult.get();

            // TODO password encryption & decryption
            return user.getContactInfo().getEmail().equals(email) && user.getPassword().equals(password);
        }

        return false;
    }

    /**
     * Return a list of all users from the database
     * @return a list of all users from the database
     */
    @Override
    public List<User> fetchAllUsers() {
        List<UserDatabaseEntity> dbUsers = this.repository.findAll();

        List<User> users = new ArrayList<>();

        for (UserDatabaseEntity dbUser : dbUsers) {
            // TODO factory method
            User user = new User(dbUser.getFirstName(), dbUser.getLastName(), dbUser.getCurrentAddress(),
                    dbUser.getCurrentCity(), dbUser.getPassword(), dbUser.getContactInfo().getEmail()) {};
            users.add(user);
        }

        return users;
    }

}
