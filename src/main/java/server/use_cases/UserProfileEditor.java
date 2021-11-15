package server.use_cases;

import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.ResponseModel;
import server.use_cases.repo_abstracts.UserNotFoundException;

/**
 * A use case responsible for editing a user's profile.
 */
public class UserProfileEditor implements UserProfileEditorInputBoundary {

    IUserRepository userRepository;

    public UserProfileEditor(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a UserProfileEditorResponseModel with given request.
     *
     * @param request Object containing new profile information of the user;
     * @return a UserProfileEditorResponseModel that contains the edited information of the user's profile.
     */
    @Override
    public ResponseModel editUserProfile(UserProfileEditorRequestModel request) {
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

        boolean isSuccess = userRepository.editUserProfile(
            id,
            request.getNewBiography(),
            request.getNewPhoneNumber(),
            request.getNewInstagram(),
            request.getNewFacebook()
        );

        if (isSuccess) {
            return new ResponseModel(
                true,
                "Successfully edited user account.",
                new UserProfileEditorResponseModel(
                    request.getUserId(),
                    request.getNewBiography(),
                    request.getNewPhoneNumber(),
                    request.getNewInstagram(),
                    request.getNewFacebook()
                )
            );
        }

        return new ResponseModel(true, "An unexpected error occurred.");
    }
}
