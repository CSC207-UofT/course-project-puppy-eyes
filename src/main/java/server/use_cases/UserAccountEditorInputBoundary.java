package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

/**
 * An input boundary for the "edit user account" use case.
 */
public interface UserAccountEditorInputBoundary {
    public ResponseModel editUserAccount(UserAccountEditorRequestModel request);
}
