package server.use_cases.pet_swipes_fetcher;

import server.use_cases.repo_abstracts.ResponseModel;

public interface PetSwipesFetcherInputBoundary {

    public ResponseModel fetchPetSwipes(PetSwipesFetcherRequestModel request);

}
