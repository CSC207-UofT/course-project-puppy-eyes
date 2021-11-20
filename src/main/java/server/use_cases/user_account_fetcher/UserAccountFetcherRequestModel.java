package server.use_cases.user_account_fetcher;

import server.use_cases.repo_abstracts.AuthRequestModel;

/**
 * An object defining the request type for
 * UserAccountFetcherInputBoundary.fetchUserAccount
 */
public class UserAccountFetcherRequestModel extends AuthRequestModel {
    private final String userId;

    public UserAccountFetcherRequestModel(String headerUserId, String userId) {
        super(headerUserId, userId);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
