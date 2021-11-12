package server.use_cases;

/**
 * An input boundary for the "fetch pet" use case.
 */
public interface PetProfileFetcherInputBoundary {
    PetProfileFetcherResponseModel fetchPetProfile(PetProfileFetcherRequestModel request);
}
