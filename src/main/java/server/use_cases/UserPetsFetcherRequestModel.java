package server.use_cases;

/**
 * An object defining the request type for
 * UserPetsFetcherInputBoundary.fetchUserPets
 */
public class UserPetsFetcherRequestModel {
    private final String userId;

    public UserPetsFetcherRequestModel(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
