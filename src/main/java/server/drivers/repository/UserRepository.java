package server.drivers.repository;

import org.springframework.stereotype.Repository;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.UserNotFoundException;
import server.use_cases.repo_abstracts.UserRepositoryUserAccountFetcherResponse;
import server.drivers.dbEntities.ContactInfoDatabaseEntity;
import server.drivers.dbEntities.UserDatabaseEntity;

import java.util.List;
import java.util.Optional;

// Defines how we interact with the database
@Repository
public class UserRepository implements IUserRepository {

    private final JpaUserRepository repository;

    public UserRepository(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public int createUser(String firstName, String lastName, String homeAddress, String password, String email) {
        ContactInfoDatabaseEntity contactInfoDbEntity = new ContactInfoDatabaseEntity("", email, "", "");
        UserDatabaseEntity userDbEntity = new UserDatabaseEntity(firstName, lastName, password, homeAddress, "", contactInfoDbEntity);
        repository.save(userDbEntity);

        return userDbEntity.getId();
    }

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

    public List<UserDatabaseEntity> findAll() {
        return repository.findAll();
    }
}
