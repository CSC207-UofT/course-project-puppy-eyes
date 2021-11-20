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
        String newBio = request.getNewBiography() == null ? user.getBiography() : request.getNewBiography();
        String newPhoneNum = request.getNewPhoneNumber() == null ? user.getContactInfo().getPhoneNumber() : request.getNewPhoneNumber();
        String newInstagram = request.getNewInstagram() == null ? user.getContactInfo().getInstagram() : request.getNewInstagram();
        String newFacebook = request.getNewFacebook() == null ? user.getContactInfo().getFacebook() : request.getNewFacebook();

        boolean isSuccess = userRepository.editUserProfile(
            id,
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
