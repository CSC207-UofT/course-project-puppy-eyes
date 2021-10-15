package server;

public interface UserAccountFetcherInputBoundary {
    UserAccountFetcherResponseModel fetchUserAccount(UserAccountFetcherRequestModel request);
}
