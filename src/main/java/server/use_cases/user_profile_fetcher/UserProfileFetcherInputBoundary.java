package server.use_cases.user_profile_fetcher;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "edit user profile" use case.
 */
public interface UserProfileFetcherInputBoundary {
    public ResponseModel fetchUserProfile(UserProfileFetcherRequestModel request);
}
