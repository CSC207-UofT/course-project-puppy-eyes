package server.use_cases.user_use_cases.user_profile_image_changer;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "user profile image changer" use case.
 */
public interface UserProfileImageChangerInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request
     * @return a ResponseModel object
     */
    ResponseModel changeProfileImage(UserProfileImageChangerRequestModel request);

}