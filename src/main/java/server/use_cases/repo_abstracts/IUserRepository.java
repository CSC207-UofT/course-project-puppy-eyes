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
     *
     * @param user the user object to save to database
     * @return The id of the new user
     */
    public int createUser(User user);

    /**
     * Fetch a User object given a user id.
     *
     * @param userId
     * @return a User object
     */
    public User fetchUser(int userId);

    /**
     * Edit a user's Account given user id and new information.
     *
     * @param userId       the user's id
     * @param newFirstName the user's new first name
     * @param newLastName  the user's new last name
     * @param newAddress   the user's new current address
     * @param newCity      the user's new current city
     * @param newPassword  the user's new password
     * @param newEmail     the user's new email
     * @param newLat       the user's new latitude
     * @param newLng       the user's new longitude
     * @return if the editing is successfully done or not
     */
    public boolean editUserAccount(int userId, String newFirstName, String newLastName, String newAddress, String newCity, String newPassword, String newEmail, String newLat, String newLng);

    /**
     * Edit a user's profile given user id and new information.
     *
     * @param userId         the user's id
     * @param newBiography   the user's new entered biography
     * @param newPhoneNumber the user's new entered phone number
     * @param newInstagram   the user's new entered Instagram
     * @param newFacebook    the user's new entered Facebook
     * @return if the editing is successfully done or not
     */
    public boolean editUserProfile(int userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook);

    /**
     * Return a list of all users from the database
     *
     * @return a list of all users from the database
     */
    public List<User> fetchAllUsers();

    /**
     * Return the user id corresponding to the given email
     *
     * @param email
     * @return user id
     */
    public int fetchIdFromEmail(String email);

    /**
     * Return a list of pet ids of pets that belong to this user
     *
     * @param userId
     * @return a list of pet ids
     */
    public List<Integer> fetchUserPets(int userId);

}

