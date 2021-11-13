package server.use_cases;

import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.UserNotFoundException;
import server.use_cases.repo_abstracts.UserRepositoryUserAccountFetcherResponse;

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
    public UserAccountFetcherResponseModel fetchUserAccount(UserAccountFetcherRequestModel request) {
        int id;
        try {
            id = Integer.parseInt(request.getUserId());
        } catch (NumberFormatException e) {
            // Then the user id string was not valid
            return new UserAccountFetcherResponseModel(false, "", "", "", "", "");
        }

        UserRepositoryUserAccountFetcherResponse user;
        try {
            user = userRepository.fetchUserAccount(id);

            return new UserAccountFetcherResponseModel(true, user.getFirstName(), user.getLastName(),
                    user.getCurrentAddress(), user.getCurrentCity(), user.getEmail());

        } catch (UserNotFoundException e) {
            // Then user not found
            return new UserAccountFetcherResponseModel(false, "", "", "", "", "");
        }

    }
}
