package server.use_cases.pet_matches_fetcher;

import server.use_cases.repo_abstracts.ResponseModel;

public interface PetMatchesFetcherInputBoundary {

    public ResponseModel fetchPetMatches(PetMatchesFetcherRequestModel request);

}
