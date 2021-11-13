package server.controllers;

/**
 * An interface representing a controller that handles all
 * functions relating to user data.
 */
public interface IUserController {
    /**
     * Create a new user and return a response in the form of a JSON string.
     *
     * @param firstName         user's first name
     * @param lastName          user's last name
     * @param currentAddress    user's current address
     * @param currentCity       user's current city
     * @param password          user's password
     * @param email             user's email
     */
    String createUser(String firstName, String lastName, String currentAddress, String currentCity,
                      String password, String email);

    /**
     * Fetch a user's account details (first name, last name, home address, email)
     * given their user id. The returned response is in the form of a JSON object.
     *
     * @param userId    the user's id
     */
    String fetchUserAccount(String userId);

    /**
     * Edit a user's account details given their user id and new information.
     *
     * @param userId        the user's id
     * @param newFirstName  the user's new first name
     * @param newLastName   the user's new last name
     * @param newAddress    the user's new current address
     * @param newCity       the user's new current city
     * @param newPassword   the user's new password
     * @param newEmail      the user's new email
     * @return a JSON object
     */
    String editUserAccount(String userId, String newFirstName, String newLastName, String newAddress,
                           String newCity, String newPassword, String newEmail);

    /**
     * Fetch a user's profile details (first name, last name, biography, phone number, email, Instagram, Facebook)
     * given their user id. The returned response is in the form of a JSON object.
     *
     * @param userId    the user's id
     * @return a JSON object
     */
    String fetchUserProfile(String userId);

    /**
     * Edit a user's profile details given their user id and new information.
     *
     * @param userId            the user's id
     * @param newBiography      the user's new biography
     * @param newPhoneNumber    the user's new phone number
     * @param newInstagram      the user's new Instagram
     * @param newFacebook       the user's new Facebook
     * @return A JSON object
     */
    String editUserProfile(String userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook);
}
