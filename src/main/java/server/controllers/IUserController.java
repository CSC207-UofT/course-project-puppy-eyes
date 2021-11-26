package server.controllers;

import server.use_cases.ResponseModel;

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
     * @return A ResponseModel containing the response data
     */
    ResponseModel createUser(String firstName, String lastName, String currentAddress, String currentCity,
                             String password, String email);

    /**
     * Fetch a user's account details (firstName, lastName, currentAddress, currentCity, email)
     * given their user id. The returned response is in the form of a JSON object.
     *
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId
     * @return A ResponseModel containing the response data
     */
    ResponseModel fetchUserAccount(boolean fromTerminal, String headerUserId, String userId);

    /**
     * Return a list of pet ids that belong to the user
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId
     * @return A ResponseModel containing the response data
     */
    ResponseModel fetchUserPets(boolean fromTerminal, String headerUserId, String userId);

    /**
     * Edit a user's account details given their user id and new information.
     *
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId        the user's id
     * @param newFirstName  the user's new first name
     * @param newLastName   the user's new last name
     * @param newAddress    the user's new current address
     * @param newCity       the user's new current city
     * @param newPassword   the user's new password
     * @param newEmail      the user's new email
     * @return A ResponseModel containing the response data
     */
    ResponseModel editUserAccount(boolean fromTerminal, String headerUserId, String userId, String newFirstName, String newLastName,
                           String newAddress, String newCity, String newPassword, String newEmail);

    /**
     * Fetch a user's profile details (first name, last name, biography, phone number, email, Instagram, Facebook)
     * given their user id. The returned response is in the form of a JSON object.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId    the user's id
     * @return A ResponseModel containing the response data
     */
    ResponseModel fetchUserProfile(boolean fromTerminal, String headerUserId, String userId);

    /**
     * Edit a user's profile details given their user id and new information.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId            the user's id
     * @param newBiography      the user's new biography
     * @param newPhoneNumber    the user's new phone number
     * @param newInstagram      the user's new Instagram
     * @param newFacebook       the user's new Facebook
     * @return A ResponseModel containing the response data
     */
    ResponseModel editUserProfile(boolean fromTerminal, String headerUserId, String userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook);

    /**
     * Set's a user's profile picture to the image represented by the Base64 encoding.
     * @param headerUserId
     * @param base64Encoded
     * @return a ResponseModel containing the response data
     */
    ResponseModel setUserProfile(String headerUserId, String base64Encoded);

}
