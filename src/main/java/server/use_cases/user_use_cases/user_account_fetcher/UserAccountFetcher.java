package server.use_cases.user_use_cases.user_account_fetcher;

import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.ResponseModel;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidatorInputBoundary;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidatorRequestModel;

/**
 * A use case responsible for fetching a user's account based on a user id.
 */
public class UserAccountFetcher implements UserAccountFetcherInputBoundary {

    private final IUserRepository userRepository;
    private final UserActionValidatorInputBoundary userActionValidator;

    public UserAccountFetcher(IUserRepository userRepository, UserActionValidatorInputBoundary userActionValidator) {
        this.userRepository = userRepository;
        this.userActionValidator = userActionValidator;
    }

    /**
     * Fetch a user's account.
     * @param request Object containing the user's id.
     * @return An object containing the user's account information.
     */
    @Override
    public ResponseModel fetchUserAccount(UserAccountFetcherRequestModel request) {
        ResponseModel validateActionResponse = userActionValidator.validateAction(new UserActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getUserId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int userId = Integer.parseInt(request.getUserId());
        User user = userRepository.fetchUser(userId);

        if (!request.isRequestAuthorized()) {
            return new ResponseModel(false, "You are not authorized to make this request.");
        }

        return new ResponseModel(
            true,
            "Successfully fetched user account.",
            new UserAccountFetcherResponseModel(
                user.getFirstName(),
                user.getLastName(),
                user.getCurrentAddress(),
                user.getCurrentCity(),
                user.getContactInfo().getEmail()
            )
        );
    }
}
