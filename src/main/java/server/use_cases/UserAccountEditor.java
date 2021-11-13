package server.use_cases;

import server.use_cases.repo_abstracts.IUserRepository;


/**
 * A use case responsible for editing a user's account.
 */
public class UserAccountEditor implements UserAccountEditorInputBoundary {
    IUserRepository userRepository;

    public UserAccountEditor(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a UserAccountEditorResponseModel with given request.
     *
     * @param request Object containing new information of the user;
     * @return a UserAccountEditorResponseModel that contains the edited information of the pet.
     */
    @Override
    public UserAccountEditorResponseModel editUserAccount(UserAccountEditorRequestModel request) {
        boolean isSuccess = userRepository.editUserAccount(Integer.parseInt(request.getUserId()),
                request.getNewFirstName(),
                request.getNewLastName(),
                request.getNewAddress(),
                request.getNewCity(),
                request.getNewPassword(),
                request.getNewEmail());

        return new UserAccountEditorResponseModel(isSuccess,
                request.getUserId(),
                request.getNewFirstName(),
                request.getNewLastName(),
                request.getNewAddress(),
                request.getNewCity(),
                request.getNewPassword(),
                request.getNewEmail());
    }
}
