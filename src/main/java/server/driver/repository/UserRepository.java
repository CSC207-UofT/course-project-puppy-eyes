package server.driver.repository;

import org.springframework.stereotype.Repository;
import server.IUserRepository;
import server.driver.dbEntities.ContactInfoDatabaseEntity;
import server.driver.dbEntities.UserDatabaseEntity;

import java.util.List;

// Defines how we interact with the database
@Repository
public class UserRepository implements IUserRepository {

    private final JpaUserRepository repository;

    public UserRepository(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String firstName, String lastName, String homeAddress, String password, String email) {
        ContactInfoDatabaseEntity contactInfoDbEntity = new ContactInfoDatabaseEntity("", "", "", "");
        UserDatabaseEntity userDbEntity = new UserDatabaseEntity(firstName, lastName, password, homeAddress, "", contactInfoDbEntity);
        repository.save(userDbEntity);
    }

    public List<UserDatabaseEntity> findAll() {
        return repository.findAll();
    }
}
