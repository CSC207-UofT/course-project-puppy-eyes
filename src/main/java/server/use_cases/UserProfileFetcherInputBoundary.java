package server.use_cases;

/**
 * An input boundary for the "edit user profile" use case.
 */
public interface UserProfileFetcherInputBoundary {
    UserProfileFetcherResponseModel fetchUserProfile(UserProfileFetcherRequestModel request);
}
