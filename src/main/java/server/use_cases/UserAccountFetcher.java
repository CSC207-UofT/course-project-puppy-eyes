package server.use_cases;

import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.UserNotFoundException;
import server.use_cases.repo_abstracts.UserRepositoryUserAccountFetcherResponse;

public class UserAccountFetcher implements UserAccountFetcherInputBoundary {
    private IUserRepository userRepository;

    public UserAccountFetcher(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserAccountFetcherResponseModel fetchUserAccount(UserAccountFetcherRequestModel request) {
        int id;
        try {
            id = Integer.parseInt(request.getUserId());
        } catch (NumberFormatException e) {
            // Then the user id string was not valid
            return new UserAccountFetcherResponseModel(false, "", "", "", "");
        }

        UserRepositoryUserAccountFetcherResponse user;
        try {
            user = userRepository.fetchUserAccount(id);

            return new UserAccountFetcherResponseModel(true, user.getFirstName(), user.getLastName(),
                    user.getHomeAddress(), user.getEmail());

        } catch (UserNotFoundException e) {
            // Then user not found
            return new UserAccountFetcherResponseModel(false, "", "", "", "");
        }

    }
}
