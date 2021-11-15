package server.use_cases;

import server.use_cases.repo_abstracts.AuthRequestModel;

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
