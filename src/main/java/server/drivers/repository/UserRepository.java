package server.drivers.repository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import server.drivers.IPasswordEncryptor;
import server.drivers.dbEntities.PetDatabaseEntity;
import server.entities.User;
import server.entities.UserFactory;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.UserNotFoundException;
import server.drivers.dbEntities.ContactInfoDatabaseEntity;
import server.drivers.dbEntities.UserDatabaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     *
     * @param user  the user
     *
     * @return The id of the new user.
     */
    @Override
    public int createUser(User user) {
        ContactInfoDatabaseEntity contactInfoDbEntity = new ContactInfoDatabaseEntity(
                user.getContactInfo().getPhoneNumber(),
                user.getContactInfo().getEmail(),
                user.getContactInfo().getInstagram(),
                user.getContactInfo().getFacebook()
        );
        UserDatabaseEntity userDbEntity = new UserDatabaseEntity(
                user.getFirstName(),
                user.getLastName(),
                user.getPasswordHash(),
                user.getCurrentAddress(),
                user.getCurrentCity(),
                user.getMatchingDistanceCap(),
                user.getBiography(),
                user.getType(),
                contactInfoDbEntity
        );
        repository.save(userDbEntity);

        return userDbEntity.getId();
    }

    @Override
    public User fetchUser(int userId) throws UserNotFoundException {
        Optional<UserDatabaseEntity> searchResult = repository.findById(userId);

        if (searchResult.isPresent()) {
            UserDatabaseEntity dbUser = searchResult.get();

            UserFactory userFactory = new UserFactory();
            User user = userFactory.createUser(
                dbUser.getType(),
                dbUser.getFirstName(),
                dbUser.getLastName(),
                dbUser.getCurrentAddress(),
                dbUser.getCurrentCity(),
                dbUser.getPassword(),
                dbUser.getContactInfo().getEmail()
            );
            user.setId(dbUser.getId());
            user.setBiography(dbUser.getBiography());
            user.getContactInfo().setPhoneNumber(dbUser.getContactInfo().getPhoneNumber());
            user.getContactInfo().setFacebook(dbUser.getContactInfo().getFacebook());
            user.getContactInfo().setInstagram(dbUser.getContactInfo().getInstagram());
            return user;
        } else {
            throw new UserNotFoundException("User of ID: " + userId + " not found");
        }
    }

    /**
     * Edit a user's Account given user id and new information.
     *
     * @param userId        the user's id
     * @param newFirstName  the user's new first name
     * @param newLastName   the user's new last name
     * @param newAddress    the user's new current address
     * @param newCity       the user's new current city
     * @param newPassword   the user's new password
     * @param newEmail      the user's new email
     *
     * @return if the editing is successfully done or not
     */
    @Override
    public boolean editUserAccount(int userId, String newFirstName, String newLastName, String newAddress, String newCity, String newPassword, String newEmail) {
        Optional<UserDatabaseEntity> searchResult = repository.findById(userId);

        if (searchResult.isPresent()) {
            UserDatabaseEntity user = searchResult.get();
            ContactInfoDatabaseEntity userContactInfo = user.getContactInfo();

            user.setFirstName(newFirstName);
            user.setLastName(newLastName);
            user.setCurrentAddress(newAddress);
            user.setCurrentCity(newCity);
            user.setPassword(newPassword);

            userContactInfo.setEmail(newEmail);

            repository.save(user);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Edit a user's profile given user id and new information.
     *
     * @param userId         the user's id
     * @param newBiography   the user's new entered biography
     * @param newPhoneNumber the user's new entered phone number
     * @param newInstagram   the user's new entered Instagram
     * @param newFacebook    the user's new entered Facebook
     *
     * @return if the editing is successfully done or not
     */
    @Override
    public boolean editUserProfile(int userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook) {
        Optional<UserDatabaseEntity> searchResult = repository.findById(userId);


        if (searchResult.isPresent()) {
            UserDatabaseEntity user = searchResult.get();
            ContactInfoDatabaseEntity userContactInfo = user.getContactInfo();

            user.setBiography(newBiography);

            userContactInfo.setPhoneNumber(newPhoneNumber);
            userContactInfo.setInstagram(newInstagram);
            userContactInfo.setFacebook(newFacebook);

            repository.save(user);

            return true;
        } else {
            return false;
        }
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
            UserFactory userFactory = new UserFactory();
            User user = userFactory.createUser(
                dbUser.getType(),
                dbUser.getFirstName(),
                dbUser.getLastName(),
                dbUser.getCurrentAddress(),
                dbUser.getCurrentCity(),
                dbUser.getPassword(),
                dbUser.getContactInfo().getEmail()
            );

            user.setId(dbUser.getId());
            user.getContactInfo().setFacebook(dbUser.getContactInfo().getFacebook());
            user.getContactInfo().setInstagram(dbUser.getContactInfo().getInstagram());
            user.getContactInfo().setPhoneNumber(dbUser.getContactInfo().getPhoneNumber());
            users.add(user);
        }

        return users;
    }

    /**
     * Return the user id corresponding to the given email
     * @param email
     * @return user id, -1 if non-existent
     */
    @Override
    public int fetchIdFromEmail(String email) {
        Optional<UserDatabaseEntity> searchResult = Optional.ofNullable(repository.findByContactInfo_email(email));

        if (searchResult.isPresent()) {
            UserDatabaseEntity user = searchResult.get();
            return user.getId();
        }

        return -1;
    }

    @Override
    public List<Integer> fetchUserPets(int userId) throws UserNotFoundException {
        Optional<UserDatabaseEntity> searchResult = repository.findById(userId);

        if (searchResult.isPresent()) {
            UserDatabaseEntity user = searchResult.get();
            // Collect only the pet ids from the database entities
            List<Integer> petIds = user.getPets().stream().map(PetDatabaseEntity::getId).collect(Collectors.toList());
            return petIds;
        } else {
            throw new UserNotFoundException("User of ID: " + userId + " not found");
        }
    }

}
