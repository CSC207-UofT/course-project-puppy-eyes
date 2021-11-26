package server.use_cases.user_profile_editor;

import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.ResponseModel;
import server.use_cases.user_action_validator.UserActionValidatorInputBoundary;
import server.use_cases.user_action_validator.UserActionValidatorRequestModel;

/**
 * A use case responsible for editing a user's profile.
 */
public class UserProfileEditor implements UserProfileEditorInputBoundary {

    private final IUserRepository userRepository;
    private final UserActionValidatorInputBoundary userActionValidator;

    public UserProfileEditor(IUserRepository userRepository, UserActionValidatorInputBoundary userActionValidator) {
        this.userRepository = userRepository;
        this.userActionValidator = userActionValidator;
    }

    /**
     * Create a UserProfileEditorResponseModel with given request.
     *
     * @param request Object containing new profile information of the user;
     * @return a UserProfileEditorResponseModel that contains the edited information of the user's profile.
     */
    @Override
    public ResponseModel editUserProfile(UserProfileEditorRequestModel request) {
        ResponseModel validateActionResponse = userActionValidator.validateAction(new UserActionValidatorRequestModel(
                request.getHeaderUserId(), request.getUserId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int userId = Integer.parseInt(request.getUserId());
        User user = userRepository.fetchUser(userId);

        // Do not modify null fields
        String newBio = request.getNewBiography() == null ? user.getBiography() : request.getNewBiography();
        String newPhoneNum = request.getNewPhoneNumber() == null ? user.getContactInfo().getPhoneNumber() : request.getNewPhoneNumber();
        String newInstagram = request.getNewInstagram() == null ? user.getContactInfo().getInstagram() : request.getNewInstagram();
        String newFacebook = request.getNewFacebook() == null ? user.getContactInfo().getFacebook() : request.getNewFacebook();

        boolean isSuccess = userRepository.editUserProfile(
            userId,
            newBio,
            newPhoneNum,
            newInstagram,
            newFacebook
        );

        if (isSuccess) {
            return new ResponseModel(
                true,
                "Successfully edited user profile.",
                new UserProfileEditorResponseModel(
                    request.getUserId(),
                    newBio,
                    newPhoneNum,
                    newInstagram,
                    newFacebook
                )
            );
        }

        return new ResponseModel(true, "An unexpected error occurred.");
    }
}
