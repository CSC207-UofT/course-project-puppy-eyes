package server.use_cases.pet_use_cases.pet_matches_fetcher;

import server.use_cases.AuthRequestModel;

/**
 * An object defining the request type for
 * PetMatchesFetcherInputBoundary.fetchPetMatches
 */
public class PetMatchesFetcherRequestModel extends AuthRequestModel {
    private final String petId;

    public PetMatchesFetcherRequestModel(String headerUserId, String petId) {
        super(headerUserId);
        this.petId = petId;
    }

    public String getPetId() {
        return petId;
    }
}
