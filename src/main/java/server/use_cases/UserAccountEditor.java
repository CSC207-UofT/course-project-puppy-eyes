package server.use_cases;

import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.ResponseModel;
import server.use_cases.repo_abstracts.UserNotFoundException;

/**
 * A use case responsible for editing a user's account.
 */
public class UserAccountEditor implements UserAccountEditorInputBoundary {
    IUserRepository userRepository;

    public UserAccountEditor(IUserRepository userRepository) {
        this.userRepository = userRepository;
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

        try {
            userRepository.fetchUser(id);
        } catch (UserNotFoundException exception) {
            return new ResponseModel(false, "User with ID: " + request.getUserId() + " does not exist.");
        }

        if (!request.isRequestAuthorized()) {
            return new ResponseModel(false, "You are not authorized to make this request.");
        }

        boolean isSuccess = userRepository.editUserAccount(
            id,
            request.getNewFirstName(),
            request.getNewLastName(),
            request.getNewAddress(),
            request.getNewCity(),
            request.getNewPassword(),
            request.getNewEmail()
        );

        if (isSuccess) {
            return new ResponseModel(
                true,
                "Successfully edited user account.",
                new UserAccountEditorResponseModel(
                    request.getUserId(),
                    request.getNewFirstName(),
                    request.getNewLastName(),
                    request.getNewAddress(),
                    request.getNewCity(),
                    request.getNewPassword(),
                    request.getNewEmail()
                )
            );
        }

        return new ResponseModel(true, "An unexpected error occurred.");
    }
}
