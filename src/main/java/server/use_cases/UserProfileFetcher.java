package server.use_cases;

import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.UserNotFoundException;
import server.use_cases.repo_abstracts.UserRepositoryUserProfileFetcherResponse;

/**
 * A use case responsible for fetching a user's profile based on a user id.
 */
public class UserProfileFetcher implements UserProfileFetcherInputBoundary {
    private final IUserRepository userRepository;

    public UserProfileFetcher(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Fetch a user's profile.
     *
     * @param request Object containing the user's id.
     * @return An object containing the user's profile information.
     */
    @Override
    public UserProfileFetcherResponseModel fetchUserProfile(UserProfileFetcherRequestModel request) {
        int id;
        try {
            id = Integer.parseInt(request.getUserId());
        } catch (NumberFormatException e) {
            // Invalid user id
            return new UserProfileFetcherResponseModel(false, "", "", "",
                    "", "", "", "");
        }

        UserRepositoryUserProfileFetcherResponse user;
        try {
            user = userRepository.fetchUserProfile(id);

            return new UserProfileFetcherResponseModel(true, user.getFirstName(),
                    user.getLastName(), user.getBiography(),
                    user.getPhoneNumber(), user.getEmail(),
                    user.getInstagram(), user.getFacebook());
        } catch (UserNotFoundException e) {
            // User not found
            return new UserProfileFetcherResponseModel(false, "", "", "", "", "", "", "");
        }
    }
}
