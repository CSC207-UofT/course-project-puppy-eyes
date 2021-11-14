package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

public interface UserPetsFetcherInputBoundary {

    public ResponseModel fetchUserPets(UserPetsFetcherRequestModel request);

}
