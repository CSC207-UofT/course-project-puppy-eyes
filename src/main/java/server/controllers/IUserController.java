package server.controllers;

/**
 * An interface representing a controller that handles all
 * functions relating to user data.
 */
public interface IUserController {
    /**
     * Create a new user and return a response in the form of a JSON string
     *
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param homeAddress The user's home address
     * @param password The user's password
     * @param email The user's email
     */
    String createUser(String firstName, String lastName,
                                        String homeAddress, String password, String email);

    /**
     * Fetch a user's account details (first name, last name, home address, email)
     * given their user id. The returned response is in the form of a JSON object.
     *
     * @param userId The user's id
     */
    String fetchUserAccount(String userId);
}