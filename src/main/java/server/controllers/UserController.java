package server.controllers;

import server.use_cases.ResponseModel;
import server.adapters.UseCaseOutputBoundary;
import server.use_cases.profile_image_fetcher.ProfileImageFetcherInputBoundary;
import server.use_cases.profile_image_fetcher.ProfileImageFetcherRequestModel;
import server.use_cases.user_use_cases.user_account_editor.UserAccountEditorInputBoundary;
import server.use_cases.user_use_cases.user_account_editor.UserAccountEditorRequestModel;
import server.use_cases.user_use_cases.user_account_fetcher.UserAccountFetcherInputBoundary;
import server.use_cases.user_use_cases.user_account_fetcher.UserAccountFetcherRequestModel;
import server.use_cases.user_use_cases.user_creator.UserCreatorInputBoundary;
import server.use_cases.user_use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_use_cases.user_pets_fetcher.UserPetsFetcherInputBoundary;
import server.use_cases.user_use_cases.user_pets_fetcher.UserPetsFetcherRequestModel;
import server.use_cases.user_use_cases.user_profile_editor.UserProfileEditorInputBoundary;
import server.use_cases.user_use_cases.user_profile_editor.UserProfileEditorRequestModel;
import server.use_cases.user_use_cases.user_profile_fetcher.UserProfileFetcherInputBoundary;
import server.use_cases.user_use_cases.user_profile_fetcher.UserProfileFetcherRequestModel;
import server.use_cases.user_use_cases.user_profile_image_changer.UserProfileImageChangerInputBoundary;
import server.use_cases.user_use_cases.user_profile_image_changer.UserProfileImageChangerRequestModel;

/**
 * A controller that handles all functions relating to user data.
 */
public class UserController implements IUserController {

    UserCreatorInputBoundary userCreator;
    UserAccountFetcherInputBoundary accountFetcher;
    UserAccountEditorInputBoundary accountEditor;
    UserProfileFetcherInputBoundary profileFetcher;
    UserProfileEditorInputBoundary profileEditor;
    UserPetsFetcherInputBoundary userPetsFetcher;
    UserProfileImageChangerInputBoundary userProfileImageChanger;
    ProfileImageFetcherInputBoundary profileImageFetcher;
    UseCaseOutputBoundary responsePresenter;

    public UserController(UserCreatorInputBoundary userCreator,
                          UserAccountFetcherInputBoundary accountFetcher,
                          UserAccountEditorInputBoundary accountEditor,
                          UserProfileFetcherInputBoundary profileFetcher,
                          UserProfileEditorInputBoundary profileEditor,
                          UserPetsFetcherInputBoundary userPetsFetcher,
                          UserProfileImageChangerInputBoundary userProfileImageChanger,
                          ProfileImageFetcherInputBoundary profileImageFetcher,
                          UseCaseOutputBoundary responsePresenter) {
        this.userCreator = userCreator;
        this.accountFetcher = accountFetcher;
        this.accountEditor = accountEditor;
        this.profileFetcher = profileFetcher;
        this.profileEditor = profileEditor;
        this.userPetsFetcher = userPetsFetcher;
        this.userProfileImageChanger = userProfileImageChanger;
        this.profileImageFetcher = profileImageFetcher;
        this.responsePresenter = responsePresenter;
    }

    /**
     * Create a new user. Return a JSON structure containing:
     * {
     *      isSuccess: "true"/"false"
     *      // If successful, then include the following
     *      data: {
     *          userId: the new user's id
     *          firstName: the first name of new user
     *          lastName: the last name of the new user
     *          currentAddress: the current address of the new user
     *          currentCity: the current city of the new user
     *          email: the email of the new user
     *      }
     *      // else,
     *      data: null
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
    public ResponseModel createUser(String firstName, String lastName, String currentAddress, String currentCity, String password, String email) {
        UserCreatorRequestModel request = new UserCreatorRequestModel(firstName, lastName, currentAddress, currentCity, password, email);
        ResponseModel response = userCreator.createUser(request);
        return response;
    }

    /**
     * Given a user id, fetch a user's account information.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId
     * @return a JSON structure containing:
     *    {
     *       isSuccess: "true"/"false"
     *       // If successful, then include the following
     *       data: {
     *          firstName: the first name of user
     *          lastName: the last name of the user
     *          currentAddress: the current address of the user
     *          currentCity: the current city of the user
     *          email: the email of the user
     *       }
     *       // else,
     *       data: null
     *    }
     */
    @Override
    public ResponseModel fetchUserAccount(boolean fromTerminal, String headerUserId, String userId) {
        UserAccountFetcherRequestModel request = new UserAccountFetcherRequestModel(headerUserId, userId);
        request.setFromTerminal(fromTerminal);
        ResponseModel response = accountFetcher.fetchUserAccount(request);
        return response;
    }

    /**
     * Return a list of pet ids of pets that belong to the user with this user id
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId
     * @return a JSON structure containing:
     *      {
     *          isSuccess: "true"/"false",
     *          // if successful:
     *          data: {
     *              petIds: [pet_id_1, pet_id_2, ..., pet_id_n]
     *          }
     *          // else,
     *          data: null
     *      }
     */
    @Override
    public ResponseModel fetchUserPets(boolean fromTerminal, String headerUserId, String userId) {
        UserPetsFetcherRequestModel request = new UserPetsFetcherRequestModel(headerUserId, userId);
        request.setFromTerminal(fromTerminal);
        ResponseModel response = userPetsFetcher.fetchUserPets(request);
        return response;
    }

    /**
     * Edit a user's account details given their user id and new information.
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
     * @return a JSON structure containing:
     *    {
     *       isSuccess: "true"/"false"
     *       // If successful, then include the following
     *       data: {
     *          userId: the user's id
     *          newFirstName: the new first name of user
     *          newLastName: the new last name of the user
     *          newCurrentAddress: the new current address of the user
     *          newCurrentCity: the new current city of the user
     *          newEmail: the new email of the user
     *       }
     *       // else,
     *       data: null
     *    }
     */
    @Override
    public ResponseModel editUserAccount(boolean fromTerminal, String headerUserId, String userId, String newFirstName, String newLastName,
                                  String newAddress, String newCity, String newPassword, String newEmail) {
        UserAccountEditorRequestModel request = new UserAccountEditorRequestModel(headerUserId, userId, newFirstName,
                newLastName, newAddress, newCity, newPassword, newEmail);
        request.setFromTerminal(fromTerminal);
        ResponseModel response = accountEditor.editUserAccount(request);
        return response;
    }

    /**
     * Fetch a user's profile details (first name, last name, biography, phone number, email, Instagram, Facebook)
     * given their user id. The returned response is in the form of a JSON object.
     *
     * @param headerUserId
     * @param userId    the user's id
     * @return a JSON structure containing:
     *    {
     *       isSuccess: "true"/"false"
     *       // If successful, then include the following
     *       data: {
     *          firstName: the first name of user
     *          lastName: the last name of the user
     *          biography: the biography of the user
     *          phoneNumber: the phone number of the user
     *          email: the email of the user
     *          instagram: the instagram of the user
     *          facebook: the facebook of the user
     *       }
     *       // else,
     *       data: null
     *    }
     */
    @Override
    public ResponseModel fetchUserProfile(boolean fromTerminal, String headerUserId, String userId) {
        UserProfileFetcherRequestModel request = new UserProfileFetcherRequestModel(headerUserId, userId);
        request.setFromTerminal(fromTerminal);
        ResponseModel response = profileFetcher.fetchUserProfile(request);
        return response;
    }

    /**
     * Edit a user's profile details given their user id and new information.
     *
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId         the user's id
     * @param newBiography   the user's new biography
     * @param newPhoneNumber the user's new phone number
     * @param newInstagram   the user's new Instagram
     * @param newFacebook    the user's new Facebook
     * @return a JSON structure containing:
     *    {
     *       isSuccess: "true"/"false"
     *       // If successful, then include the following
     *       data: {
     *          userId: the user's id
     *          newBiography: the new biography of the user
     *          newPhoneNumber: the new phone number of the user
     *          newInstagram: the new instagram of the user
     *          newFacebook: the new facebook of the user
     *       }
     *       // else,
     *       data: null
     *    }
     */
    @Override
    public ResponseModel editUserProfile(boolean fromTerminal, String headerUserId, String userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook) {
        UserProfileEditorRequestModel request = new UserProfileEditorRequestModel(headerUserId, userId, newBiography,
                newPhoneNumber, newInstagram, newFacebook);
        request.setFromTerminal(fromTerminal);
        ResponseModel response = profileEditor.editUserProfile(request);
        return response;
    }

    /**
     * Set's the user profile to the image represented by the Base64 encoding.
     * @param headerUserId
     * @param base64Encoded
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false"
     *          message: "The response message,
     *          // if successful:
     *         data: {
     *              url: the url to the image
     *              assetId: the id of the asset
     *         }
     *          // else,
     *          data: null
     *      }
     */
    @Override
    public ResponseModel setUserProfile(String headerUserId, String base64Encoded) {
        UserProfileImageChangerRequestModel request = new UserProfileImageChangerRequestModel(headerUserId, base64Encoded);
        ResponseModel response = userProfileImageChanger.changeProfileImage(request);
        return response;
    }

    /**
     * Return a URL containing this user's profile image
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId        the user's id
     * @return a ResponseModel containing:
     * {
     *       isSuccess: "true"/"false"
     *       message: The response message
     *       // If successful, then include:
     *       data: {
     *          url: the url of the image
     *       }
     *  }
     */
    public ResponseModel fetchUserProfileImage(boolean fromTerminal, String headerUserId, String userId) {
        ProfileImageFetcherRequestModel request = new ProfileImageFetcherRequestModel(headerUserId, userId, true);
        request.setFromTerminal(fromTerminal);
        return profileImageFetcher.fetchProfileImage(request);
    }

}