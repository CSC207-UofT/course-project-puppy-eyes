package server.use_cases;

/**
 * An input boundary for the "edit user account" use case.
 */
public interface UserAccountEditorInputBoundary {
    UserAccountEditorResponseModel editUserAccount(UserAccountEditorRequestModel request);
}
