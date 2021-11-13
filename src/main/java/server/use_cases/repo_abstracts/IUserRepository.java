package server.use_cases.repo_abstracts;

import server.entities.Pet;
import server.entities.User;

import java.util.List;

/**
 * An interface defining an access point from the program
 * to a repository storing the information of all users.
 */
public interface IUserRepository {

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
    public int createUser(String firstName, String lastName, String password, String currentAddress, String currentCity, String email);

    /**
     * Fetch a user's account information given a user id.
     *
     * @param userId The user's id.
     */
    public UserRepositoryUserAccountFetcherResponse fetchUserAccount(int userId) throws UserNotFoundException;

    /**
     * Return whether an email-password pair exist as credentials in the database.
     * @param email
     * @param password
     * @return true if credentials exist, false otherwise
     */
    public boolean validateCredentials(String email, String password);

    /**
     * Return a list of all users from the database
     * @return a list of all users from the database
     */
    public List<User> fetchAllUsers();

    /**
     * Return the user id corresponding to the given email
     * @param email
     * @return user id
     */
    public int fetchIdFromEmail(String email);

    /**
     * Return a list of pet ids of pets that belong to this user
     * @param userId
     * @return a list of pet ids
     */
    public List<Integer> fetchUserPets(int userId) throws UserNotFoundException;

}

