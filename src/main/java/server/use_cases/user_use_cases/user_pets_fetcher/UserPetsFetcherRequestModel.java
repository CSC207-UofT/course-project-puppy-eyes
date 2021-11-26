package server.use_cases.user_use_cases.user_pets_fetcher;

import server.use_cases.AuthRequestModel;

/**
 * An object defining the request type for
 * UserPetsFetcherInputBoundary.fetchUserPets
 */
public class UserPetsFetcherRequestModel extends AuthRequestModel {

    public UserPetsFetcherRequestModel(String headerUserId, String userId) {
        super(headerUserId, userId);
    }

}
