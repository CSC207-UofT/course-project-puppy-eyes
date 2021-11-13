package server.use_cases.repo_abstracts;

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
     * Edit a user's Account given user id and new information.
     *
     * @param userId the user's id
     * @param newFirstName the user's new first name
     * @param newLastName the user's new last name
     * @param newAddress the user's new current address
     * @param newCity the user's new current city
     * @param newPassword the user's new password
     * @param newEmail the user's new email
     * @return if the editing is successfully done or not
     */
    public boolean editUserAccount(int userId, String newFirstName, String newLastName, String newAddress, String newCity, String newPassword, String newEmail);

    /**
     * Fetch a user's profile information given a user id.
     *
     * @param userId the user's id
     * @return A UserRepositoryUserProfileFetcherResponse object containing the profile information
     * @throws UserNotFoundException
     */
    public UserRepositoryUserProfileFetcherResponse fetchUserProfile(int userId) throws UserNotFoundException;

    /**
     * Edit a user's profile given user id and new information.
     *
     * @param userId the user's id
     * @param newBiography the user's new entered biography
     * @param newPhoneNumber the user's new entered phone number
     * @param newInstagram the user's new entered Instagram
     * @param newFacebook the user's new entered Facebook
     * @return if the editing is successfully done or not
     */
    public boolean editUserProfile(int userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook);

    /**
     * Return whether an email-password pair exist as credentials in the database.
     *
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
}

