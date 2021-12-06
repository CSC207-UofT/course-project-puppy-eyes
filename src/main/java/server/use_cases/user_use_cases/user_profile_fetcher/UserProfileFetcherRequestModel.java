package server.use_cases.user_use_cases.user_profile_fetcher;

import server.use_cases.AuthRequestModel;

/**
 * An object defining the request type for
 * UserProfileFetcherInputBoundary.fetchUserProfile
 */
public class UserProfileFetcherRequestModel extends AuthRequestModel {

    public UserProfileFetcherRequestModel(String headerUserId, String userId) {
        super(headerUserId, userId);
    }

}
