package server.use_cases.user_account_editor;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "edit user account" use case.
 */
public interface UserAccountEditorInputBoundary {
    public ResponseModel editUserAccount(UserAccountEditorRequestModel request);
}
