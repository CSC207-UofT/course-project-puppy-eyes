package server.use_cases;

/**
 * An input boundary for the "fetch user account" use case.
 */
public interface UserAccountFetcherInputBoundary {
    UserAccountFetcherResponseModel fetchUserAccount(UserAccountFetcherRequestModel request);
}
