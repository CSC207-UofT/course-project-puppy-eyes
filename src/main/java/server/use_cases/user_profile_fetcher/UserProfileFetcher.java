package server.use_cases.user_profile_fetcher;

import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.ResponseModel;
import server.use_cases.repo_abstracts.UserNotFoundException;

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
    public ResponseModel fetchUserProfile(UserProfileFetcherRequestModel request) {
        int id;
        try {
            id = Integer.parseInt(request.getUserId());
        } catch (NumberFormatException e) {
            // Invalid user id
            return new ResponseModel(false, "ID must be an integer.");
        }

        try {
            User user = userRepository.fetchUser(id);

            return new ResponseModel(
                true,
                "Sucessfully fetched user profile.",
                new UserProfileFetcherResponseModel(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getBiography(),
                    user.getContactInfo().getPhoneNumber(),
                    user.getContactInfo().getEmail(),
                    user.getContactInfo().getInstagram(),
                    user.getContactInfo().getFacebook()
                )
            );
        } catch (UserNotFoundException e) {
            // User not found
            return new ResponseModel(false, "User with ID: " + id + " does not exist.");
        }
    }
}
