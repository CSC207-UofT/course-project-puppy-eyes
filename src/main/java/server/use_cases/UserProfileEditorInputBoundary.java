package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

/**
 * An input boundary for the "edit user profile" use case.
 */
public interface UserProfileEditorInputBoundary {
    public ResponseModel editUserProfile(UserProfileEditorRequestModel request);
}
