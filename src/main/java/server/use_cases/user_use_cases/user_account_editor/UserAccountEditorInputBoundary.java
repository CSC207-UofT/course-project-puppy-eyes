package server.use_cases.user_use_cases.user_account_editor;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "edit user account" use case.
 */
public interface UserAccountEditorInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request
     * @return a ResponseModel object
     */
    ResponseModel editUserAccount(UserAccountEditorRequestModel request);

}
