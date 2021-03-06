package server.use_cases.pet_use_cases.pet_swipes_fetcher;

import server.use_cases.ResponseModel;

public interface PetSwipesFetcherInputBoundary {

    ResponseModel fetchPetSwipes(PetSwipesFetcherRequestModel request);

}
