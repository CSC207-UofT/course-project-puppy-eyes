package server.use_cases.user_profile_fetcher;

import server.use_cases.AuthRequestModel;

/**
 * An object defining the request type for
 * UserProfileFetcherInputBoundary.fetchUserProfile
 */
public class UserProfileFetcherRequestModel extends AuthRequestModel {

    private boolean hasMutualPets;

    public UserProfileFetcherRequestModel(String headerUserId, String userId) {
        super(headerUserId, userId);
    }

    public boolean hasMutualPets() {
        return hasMutualPets;
    }

    public void setHasMutualPets(boolean hasMutualPets) {
        this.hasMutualPets = hasMutualPets;
    }

    @Override
    public boolean isRequestAuthorized() {
        return super.isRequestAuthorized() || hasMutualPets;
    }

}
