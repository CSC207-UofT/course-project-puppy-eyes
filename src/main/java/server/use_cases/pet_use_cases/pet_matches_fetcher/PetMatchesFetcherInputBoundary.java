package server.use_cases.pet_use_cases.pet_matches_fetcher;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "pet matches fetcher" use case
 */
public interface PetMatchesFetcherInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     * @param request
     * @return a ResponseModel object
     */
    ResponseModel fetchPetMatches(PetMatchesFetcherRequestModel request);

}
