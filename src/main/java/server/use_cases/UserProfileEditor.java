package server.use_cases;

import server.use_cases.repo_abstracts.IUserRepository;

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
    public UserProfileEditorResponseModel editUserProfile(UserProfileEditorRequestModel request) {
        boolean isSuccess = userRepository.editUserProfile(Integer.parseInt(request.getUserId()),
                request.getNewBiography(),
                request.getNewPhoneNumber(),
                request.getNewInstagram(),
                request.getNewFacebook());

        return new UserProfileEditorResponseModel(isSuccess,
                request.getUserId(),
                request.getNewBiography(),
                request.getNewPhoneNumber(),
                request.getNewInstagram(),
                request.getNewFacebook());
    }
}
