package server.use_cases;

public interface UserAccountFetcherInputBoundary {
    UserAccountFetcherResponseModel fetchUserAccount(UserAccountFetcherRequestModel request);
}
