package server.use_cases.user_account_fetcher;

import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.ResponseModel;
import server.use_cases.repo_abstracts.UserNotFoundException;

/**
 * A use case responsible for fetching a user's account based on a user id.
 */
public class UserAccountFetcher implements UserAccountFetcherInputBoundary {
    private final IUserRepository userRepository;

    public UserAccountFetcher(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Fetch a user's account.
     * @param request Object containing the user's id.
     * @return An object containing the user's account information.
     */
    @Override
    public ResponseModel fetchUserAccount(UserAccountFetcherRequestModel request) {
        int id;
        try {
            id = Integer.parseInt(request.getUserId());
        } catch (NumberFormatException e) {
            // Invalid user id
            return new ResponseModel(false, "ID must be an integer.");
        }

        try {
            User user = userRepository.fetchUser(id);

            if (!request.isRequestAuthorized()) {
                return new ResponseModel(false, "You are not authorized to make this request.");
            }

            return new ResponseModel(
                true,
                "Successfully fetched pet profile.",
                new UserAccountFetcherResponseModel(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getCurrentAddress(),
                    user.getCurrentCity(),
                    user.getContactInfo().getEmail()
                )
            );
        } catch (UserNotFoundException e) {
            // Then user not found
            return new ResponseModel(false, "User with ID: " + request.getUserId() + " does not exist.");
        }

    }
}
