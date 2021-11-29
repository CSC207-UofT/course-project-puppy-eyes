package server.use_cases.pet_use_cases.pet_matches_fetcher;

import server.use_cases.ResponseModel;

public interface PetMatchesFetcherInputBoundary {

    public ResponseModel fetchPetMatches(PetMatchesFetcherRequestModel request);

}
