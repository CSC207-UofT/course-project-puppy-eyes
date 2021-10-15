package server.drivers.repository;

import org.springframework.stereotype.Repository;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.UserNotFoundException;
import server.use_cases.repo_abstracts.UserRepositoryUserAccountFetcherResponse;
import server.drivers.dbEntities.ContactInfoDatabaseEntity;
import server.drivers.dbEntities.UserDatabaseEntity;

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
     * @param homeAddress The user's home address.
     * @param password The user's password.
     * @param email The user's email.
     *
     * @return The id of the new user.
     */
    @Override
    public int createUser(String firstName, String lastName, String homeAddress, String password, String email) {
        ContactInfoDatabaseEntity contactInfoDbEntity = new ContactInfoDatabaseEntity("", email, "", "");
        UserDatabaseEntity userDbEntity = new UserDatabaseEntity(firstName, lastName, password, homeAddress, "", contactInfoDbEntity);
        repository.save(userDbEntity);

        return userDbEntity.getId();
    }

    /**
     * Retrieve a user's account information given their user id.
     *
     * @param userId The user's id.
     * @return An object containing the user's first name, last name, home address, and email.
     * @throws UserNotFoundException if no user with such an id was found
     */
    @Override
    public UserRepositoryUserAccountFetcherResponse fetchUserAccount(int userId) throws UserNotFoundException {
        Optional<UserDatabaseEntity> searchResult = repository.findById(userId);

        if (searchResult.isPresent()){
            UserDatabaseEntity user = searchResult.get();

            return new UserRepositoryUserAccountFetcherResponse(user.getFirstName(), user.getLastName(),
                    user.getHomeAddress(), user.getContactInfo().getEmail());
        }else{
            throw new UserNotFoundException("User of ID: " + userId + " not found");
        }
    }
}
