package server.use_cases.user_pets_fetcher;

import server.use_cases.ResponseModel;

public interface UserPetsFetcherInputBoundary {

    public ResponseModel fetchUserPets(UserPetsFetcherRequestModel request);

}
