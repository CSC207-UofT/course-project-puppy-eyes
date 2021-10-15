package server.use_cases;

/**
 * An object defining the request type for
 * UserAccountFetcherInputBoundary.fetchUserAccount
 */
public class UserAccountFetcherRequestModel {
    private final String userId;

    public UserAccountFetcherRequestModel(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
