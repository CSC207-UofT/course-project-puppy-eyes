package server.use_cases;

import server.use_cases.repo_abstracts.AuthRequestModel;

/**
 * An object defining the request type for
 * UserPetsFetcherInputBoundary.fetchUserPets
 */
public class UserPetsFetcherRequestModel extends AuthRequestModel {

    public UserPetsFetcherRequestModel(String headerUserId, String userId) {
        super(headerUserId, userId);
    }

}
