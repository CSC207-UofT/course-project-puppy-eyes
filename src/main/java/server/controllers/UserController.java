package server.controllers;

import server.use_cases.*;

import java.util.HashMap;

/**
 * A controller that handles all functions relating to user data.
 */
public class UserController implements IUserController {
    UserCreatorInputBoundary userCreator;
    UserAccountFetcherInputBoundary accountFetcher;
    UserAccountEditorInputBoundary accountEditor;
    UserProfileFetcherInputBoundary profileFetcher;
    UserProfileEditorInputBoundary profileEditor;
    IJSONPresenter jsonPresenter;
    UserPetsFetcherInputBoundary userPetsFetcher;

    public UserController(UserCreatorInputBoundary userCreator,
                          UserAccountFetcherInputBoundary accountFetcher,
                          UserAccountEditorInputBoundary accountEditor,
                          UserProfileFetcherInputBoundary profileFetcher,
                          UserProfileEditorInputBoundary profileEditor,
                          UserPetsFetcherInputBoundary userPetsFetcher,
                          IJSONPresenter jsonPresenter) {
        this.userCreator = userCreator;
        this.accountFetcher = accountFetcher;
        this.accountEditor = accountEditor;
        this.profileFetcher = profileFetcher;
        this.profileEditor = profileEditor;
        this.userPetsFetcher = userPetsFetcher;
        this.jsonPresenter = jsonPresenter;
    }

    /**
     * Create a new user. Return a JSON structure containing:
     * {
     *  isSuccess: "true"/"false"
     *  // If successful, then include the following
     *  firstName: give back the first name of new user
     *  lastName: give back the last name of the new user
     *  homeAddress: give back the home address of the new user
     *  email: give back the email of the new user
     * }
     *
     * @param firstName         user's first name
     * @param lastName          user's last name
     * @param currentAddress    user's current address
     * @param currentCity       user's current city
     * @param password          user's password
     * @param email             user's email
     *
     * @return The JSON response
     */
    @Override
    public String createUser(String firstName, String lastName, String currentAddress, String currentCity, String password, String email) {

        // Creating a UserCreatorRequestModel called newRequestModel with input
        UserCreatorRequestModel newRequestModel = new UserCreatorRequestModel(firstName, lastName, currentAddress, currentCity, password, email);

        UserCreatorResponseModel response = userCreator.createUser(newRequestModel);
        // Un-pack response into a map and prepare to convert to JSON
        HashMap<String, String> responseMap = new HashMap<String, String>(){{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("firstName", response.getFirstName());
            put("lastName", response.getLastName());
            put("homeAddress", response.getCurrentAddress());
            put("email", response.getEmail());
            put("userId", response.getUserId());
        }};

        return jsonPresenter.toJSON(responseMap);
    }

    /**
     * Given a user id, fetch a user's account information.
     *
     * @param userId    user's id
     *
     * Return a JSON structure containing:
     *      * {
     *      *  isSuccess: "true"/"false"
     *      *  // If successful, then include the following
     *      *  firstName: the first name of user
     *      *  lastName: the last name of the user
     *      *  homeAddress: the home address of the user
     *      *  email: the email of the user
     *      * }
     */
    @Override
    public String fetchUserAccount(String userId) {
        UserAccountFetcherRequestModel request = new UserAccountFetcherRequestModel(userId);

        UserAccountFetcherResponseModel response = accountFetcher.fetchUserAccount(request);

        // Un-pack response into a map and prepare to convert to JSON
        HashMap<String, String> responseMap = new HashMap<String, String>(){{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("firstName", response.getFirstName());
            put("lastName", response.getLastName());
            put("currentAddress", response.getCurrentAddress());
            put("currentCity", response.getCurrentCity());
            put("email", response.getEmail());
        }};

        return jsonPresenter.toJSON(responseMap);
    }

    /**
     * Return a list of pet ids of pets that belong to the user with this user id
     * @param userId
     * @return a JSON structure containing:
     *      {
     *          isSuccess: "true"/"false",
     *          // if successful:
     *          petIds: [pet_id_1, pet_id_2, ..., pet_id_n]
     *          // else,
     *          petIds: null
     *      }
     */
    @Override
    public String fetchUserPets(int userId) {
        UserPetsFetcherRequestModel request = new UserPetsFetcherRequestModel(String.valueOf(userId));
        UserPetsFetcherResponseModel response = userPetsFetcher.fetchUserPets(request);

        HashMap<String, String> responseMap = new HashMap<String, String>() {{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("petIds", jsonPresenter.toJSON(response.getPetIds()));
        }};
      
        return jsonPresenter.toJSON(responseMap);
    }

     * Edit a user's account details given their user id and new information.
     *
     * @param userId       the user's id
     * @param newFirstName the user's new first name
     * @param newLastName  the user's new last name
     * @param newAddress   the user's new current address
     * @param newCity      the user's new current city
     * @param newPassword  the user's new password
     * @param newEmail     the user's new email
     * @return a JSON object
     */
    @Override
    public String editUserAccount(String userId, String newFirstName, String newLastName, String newAddress,
                                  String newCity, String newPassword, String newEmail) {
        UserAccountEditorRequestModel request = new UserAccountEditorRequestModel(userId, newFirstName, newLastName,
                newAddress, newCity, newPassword, newEmail);
        UserAccountEditorResponseModel response = accountEditor.editUserAccount(request);

        HashMap<String, String> responseMap = new HashMap<String, String>(){{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("firstName", response.getNewFirstName());
            put("lastName", response.getNewLastName());
            put("currentAddress", response.getNewAddress());
            put("currentCity", response.getNewCity());
            put("email", response.getNewEmail());
        }};

        return jsonPresenter.toJSON(responseMap);
    }

    /**
     * Fetch a user's profile details (first name, last name, biography, phone number, email, Instagram, Facebook)
     * given their user id. The returned response is in the form of a JSON object.
     *
     * @param userId    the user's id
     * @return a JSON object
     */
    @Override
    public String fetchUserProfile(String userId) {
        UserProfileFetcherRequestModel request = new UserProfileFetcherRequestModel(userId);

        UserProfileFetcherResponseModel response = profileFetcher.fetchUserProfile(request);

        // Un-pack response into a map and prepare to convert to JSON
        HashMap<String, String> responseMap = new HashMap<String, String>(){{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("firstName", response.getFirstName());
            put("lastName", response.getLastName());
            put("biography", response.getBiography());
            put("phoneNumber", response.getPhoneNumber());
            put("email", response.getEmail());
            put("instagram", response.getInstagram());
            put("facebook", response.getFacebook());
        }};

        return jsonPresenter.toJSON(responseMap);
    }

    /**
     * Edit a user's profile details given their user id and new information.
     *
     * @param userId         the user's id
     * @param newBiography   the user's new biography
     * @param newPhoneNumber the user's new phone number
     * @param newInstagram   the user's new Instagram
     * @param newFacebook    the user's new Facebook
     * @return A JSON object
     */
    @Override
    public String editUserProfile(String userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook) {
        UserProfileEditorRequestModel request = new UserProfileEditorRequestModel(userId, newBiography, newPhoneNumber, newInstagram, newFacebook);
        UserProfileEditorResponseModel response = profileEditor.editUserProfile(request);

        HashMap<String, String> responseMap = new HashMap<String, String>(){{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("biography", response.getNewBiography());
            put("phoneNumber", response.getNewPhoneNumber());
            put("instagram", response.getNewInstagram());
            put("facebook", response.getNewFacebook());
        }};

        return jsonPresenter.toJSON(responseMap);
    }

}