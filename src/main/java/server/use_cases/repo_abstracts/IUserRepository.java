package server.use_cases.repo_abstracts;

import server.use_cases.repo_abstracts.UserNotFoundException;
import server.use_cases.repo_abstracts.UserRepositoryUserAccountFetcherResponse;

/**
 * An interface defining an access point from the program
 * to a repository storing the information of all users.
 */
public interface IUserRepository {

    /**
     * Create a new user and save it in the repository.
     * Return the new user's id.
     *
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param homeAddress The user's home address
     * @param password The user's password
     * @param email The user's email
     */
    public int createUser(String firstName, String lastName, String homeAddress, String password, String email);

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
}

