package server.controllers;

import server.use_cases.*;
import server.use_cases.repo_abstracts.ResponseModel;
import server.use_cases.repo_abstracts.ResponsePresenter;
import server.use_cases.repo_abstracts.UseCaseOutputBoundary;

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
    UseCaseOutputBoundary responsePresenter;

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
        this.responsePresenter = new ResponsePresenter(jsonPresenter);
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
    public String createUser(String firstName, String lastName, String currentAddress, String currentCity, String password, String email) {
        UserCreatorRequestModel request = new UserCreatorRequestModel(firstName, lastName, currentAddress, currentCity, password, email);
        ResponseModel response = userCreator.createUser(request);
        return responsePresenter.formatResponse(response);
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
    public String fetchUserAccount(boolean fromTerminal, String headerUserId, String userId) {
        UserAccountFetcherRequestModel request = new UserAccountFetcherRequestModel(headerUserId, userId);
        request.setFromTerminal(true);
        ResponseModel response = accountFetcher.fetchUserAccount(request);
        return responsePresenter.formatResponse(response);
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
    public String fetchUserPets(boolean fromTerminal, String headerUserId, int userId) {
        UserPetsFetcherRequestModel request = new UserPetsFetcherRequestModel(headerUserId, String.valueOf(userId));
        request.setFromTerminal(true);
        ResponseModel response = userPetsFetcher.fetchUserPets(request);
        return responsePresenter.formatResponse(response);
    }

    /**
     * Edit a user's account details given their user id and new information.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId       the user's id
     * @param newFirstName the user's new first name
     * @param newLastName  the user's new last name
     * @param newAddress   the user's new current address
     * @param newCity      the user's new current city
     * @param newPassword  the user's new password
     * @param newEmail     the user's new email
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
    public String editUserAccount(boolean fromTerminal, String headerUserId, String userId, String newFirstName, String newLastName,
                                  String newAddress, String newCity, String newPassword, String newEmail) {
        UserAccountEditorRequestModel request = new UserAccountEditorRequestModel(headerUserId, userId, newFirstName,
                newLastName, newAddress, newCity, newPassword, newEmail);
        request.setFromTerminal(true);
        ResponseModel response = accountEditor.editUserAccount(request);
        return responsePresenter.formatResponse(response);
    }

    /**
     * Fetch a user's profile details (first name, last name, biography, phone number, email, Instagram, Facebook)
     * given their user id. The returned response is in the form of a JSON object.
     *
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
    public String fetchUserProfile(String userId) {
        UserProfileFetcherRequestModel request = new UserProfileFetcherRequestModel(userId);
        ResponseModel response = profileFetcher.fetchUserProfile(request);
        return responsePresenter.formatResponse(response);
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
    public String editUserProfile(boolean fromTerminal, String headerUserId, String userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook) {
        UserProfileEditorRequestModel request = new UserProfileEditorRequestModel(headerUserId, userId, newBiography,
                newPhoneNumber, newInstagram, newFacebook);
        ResponseModel response = profileEditor.editUserProfile(request);
        return responsePresenter.formatResponse(response);
    }

}