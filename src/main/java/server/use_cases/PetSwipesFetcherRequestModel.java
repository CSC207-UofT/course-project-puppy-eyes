package server.use_cases;

import server.use_cases.repo_abstracts.AuthRequestModel;

/**
 * An object defining the request type for
 * PetSwipesFetcherInputBoundary.fetchPetSwipes
 */
public class PetSwipesFetcherRequestModel extends AuthRequestModel {
    private final String petId;

    public PetSwipesFetcherRequestModel(String headerUserId, String petId) {
        super(headerUserId);
        this.petId = petId;
    }

    public String getPetId() {
        return petId;
    }
}
