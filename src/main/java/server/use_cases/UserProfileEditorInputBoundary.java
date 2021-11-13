package server.use_cases;

/**
 * An input boundary for the "edit user profile" use case.
 */
public interface UserProfileEditorInputBoundary {
    UserProfileEditorResponseModel editUserProfile(UserProfileEditorRequestModel request);
}
