package server.use_cases;

/**
 * An object defining the request type for
 * UserProfileFetcherInputBoundary.fetchUserProfile
 */
public class UserProfileFetcherRequestModel {

    private String userId;

    public UserProfileFetcherRequestModel(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

}
