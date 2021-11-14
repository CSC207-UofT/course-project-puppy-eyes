package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

/**
 * An input boundary for the "fetch pet" use case.
 */
public interface PetProfileFetcherInputBoundary {
    ResponseModel fetchPetProfile(PetProfileFetcherRequestModel request);
}
