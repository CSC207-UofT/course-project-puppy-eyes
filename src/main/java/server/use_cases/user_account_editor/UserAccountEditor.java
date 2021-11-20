package server.use_cases.user_account_editor;

import server.drivers.IPasswordEncryptor;
import server.entities.User;
import server.use_cases.user_account_validator.UserAccountValidatorInputBoundary;
import server.use_cases.user_account_validator.UserAccountValidatorRequestModel;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.ResponseModel;
import server.use_cases.repo_abstracts.UserNotFoundException;

/**
 * A use case responsible for editing a user's account.
 */
public class UserAccountEditor implements UserAccountEditorInputBoundary {
    IUserRepository userRepository;
    IPasswordEncryptor passwordEncryptor;
    UserAccountValidatorInputBoundary userAccountValidator;

    public UserAccountEditor(IUserRepository userRepository, IPasswordEncryptor passwordEncryptor,
                             UserAccountValidatorInputBoundary userAccountValidator) {
        this.userRepository = userRepository;
        this.passwordEncryptor = passwordEncryptor;
        this.userAccountValidator = userAccountValidator;
    }

    /**
     * Create a ResponseModel with ResponseData of UserAccountEditorResponseModel for the given request.
     *
     * @param request Object containing new information of the user;
     * @return a ResponseModel with ResponseData of UserAccountEditorResponseModel,
     * which contains the edited information of the user's account.
     */
    @Override
    public ResponseModel editUserAccount(UserAccountEditorRequestModel request) {
        int id;
        try {
            id = Integer.parseInt(request.getUserId());
        } catch (NumberFormatException e) {
            // Invalid user id
            return new ResponseModel(false, "ID must be an integer.");
        }

        User user;

        try {
            user = userRepository.fetchUser(id);
        } catch (UserNotFoundException exception) {
            return new ResponseModel(false, "User with ID: " + request.getUserId() + " does not exist.");
        }

        if (!request.isRequestAuthorized()) {
            return new ResponseModel(false, "You are not authorized to make this request.");
        }

        // Do not modify null fields
        String newFirstName = request.getNewFirstName() == null ? user.getBiography() : request.getNewFirstName();
        String newLastName = request.getNewLastName() == null ? user.getLastName() : request.getNewLastName();
        String newPassword = request.getNewPassword() == null ? user.getPasswordHash() : request.getNewPassword();
        String newAddress = request.getNewAddress() == null ? user.getCurrentAddress() : request.getNewAddress();
        String newCity = request.getNewCity() == null ? user.getCurrentCity() : request.getNewCity();
        String newEmail = request.getNewEmail() == null ? user.getContactInfo().getEmail() : request.getNewEmail();

        if (userRepository.fetchIdFromEmail(request.getNewEmail()) >= 0) {
            return new ResponseModel(false, "This email is already taken.");
        }

        // Check inputs
        ResponseModel verifyInputsResponse = userAccountValidator.validateAccount(new UserAccountValidatorRequestModel(
           newFirstName, newLastName, newCity, newPassword, newEmail
        ));

        if (!verifyInputsResponse.isSuccess()) {
            return verifyInputsResponse;
        }

        boolean isSuccess = userRepository.editUserAccount(
            id,
            newFirstName,
            newLastName,
            newAddress,
            newCity,
            passwordEncryptor.encryptPassword(newPassword),
            newEmail
        );

        if (isSuccess) {
            return new ResponseModel(
                true,
                "Successfully edited user account.",
                new UserAccountEditorResponseModel(
                    request.getUserId(),
                    newFirstName,
                    newLastName,
                    newAddress,
                    newCity,
                    newPassword,
                    newEmail
                )
            );
        }

        return new ResponseModel(true, "An unexpected error occurred.");
    }
}
