package server.use_cases.pet_use_cases.pet_profile_fetcher;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "fetch pet" use case.
 */
public interface PetProfileFetcherInputBoundary {
    ResponseModel fetchPetProfile(PetProfileFetcherRequestModel request);
}
