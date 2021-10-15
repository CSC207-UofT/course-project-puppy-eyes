package server.use_cases.repo_abstracts;

import server.use_cases.repo_abstracts.UserNotFoundException;
import server.use_cases.repo_abstracts.UserRepositoryUserAccountFetcherResponse;

// Defines the methods that let us interact with the database
public interface IUserRepository {

    /**
     * Create a new user and save it in the database.
     * Return the new user's id.
     *
     * @param firstName
     * @param lastName
     * @param homeAddress
     * @param password
     * @param email
     */
    public int createUser(String firstName, String lastName, String homeAddress, String password, String email);

    public UserRepositoryUserAccountFetcherResponse fetchUserAccount(int userId) throws UserNotFoundException;
}

