package server.use_cases.pet_profile_fetcher;

import server.use_cases.AuthRequestModel;

/**
 * An object defining the request type for
 * PetProfileFetcherInputBoundary.fetchPetProfile
 */
public class PetProfileFetcherRequestModel extends AuthRequestModel {
    private final String petId;

    public PetProfileFetcherRequestModel(String headerUserId, String petId) {
        super(headerUserId);
        this.petId = petId;
    }

    public String getPetId() {
        return petId;
    }
}
