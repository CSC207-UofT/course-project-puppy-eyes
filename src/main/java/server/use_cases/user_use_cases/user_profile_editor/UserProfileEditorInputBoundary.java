package server.use_cases.user_use_cases.user_profile_editor;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "edit user profile" use case.
 */
public interface UserProfileEditorInputBoundary {
    public ResponseModel editUserProfile(UserProfileEditorRequestModel request);
}
