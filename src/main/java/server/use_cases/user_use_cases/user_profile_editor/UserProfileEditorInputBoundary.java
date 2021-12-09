package server.use_cases.user_use_cases.user_profile_editor;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "edit user profile" use case.
 */
public interface UserProfileEditorInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request the request object
     * @return a ResponseModel object
     */
    ResponseModel editUserProfile(UserProfileEditorRequestModel request);
}
