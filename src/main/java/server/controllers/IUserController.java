package server.controllers;

/**
 * An interface representing a controller that handles all
 * functions relating to user data.
 */
public interface IUserController {
    /**
     * Create a new user and return a response in the form of a JSON string
     *
     * @param firstName user's first name
     * @param lastName user's last name
     * @param currentAddress user's current address
     * @param currentCity user's current city
     * @param password user's password
     * @param email user's email
     */
    String createUser(String firstName, String lastName, String currentAddress, String currentCity,
                      String password, String email);

    /**
     * Fetch a user's account details (first name, last name, home address, email)
     * given their user id. The returned response is in the form of a JSON object.
     *
     * @param userId The user's id
     */
    String fetchUserAccount(String userId);
}
