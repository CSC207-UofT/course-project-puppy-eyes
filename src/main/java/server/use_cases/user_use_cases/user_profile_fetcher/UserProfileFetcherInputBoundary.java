package server.use_cases.user_use_cases.user_profile_fetcher;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "edit user profile" use case.
 */
public interface UserProfileFetcherInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request
     * @return a ResponseModel object
     */
    ResponseModel fetchUserProfile(UserProfileFetcherRequestModel request);

}
