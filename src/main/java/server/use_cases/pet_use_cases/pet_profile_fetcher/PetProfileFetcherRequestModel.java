package server.use_cases.pet_use_cases.pet_profile_fetcher;

/**
 * An object defining the request type for
 * PetProfileFetcherInputBoundary.fetchPetProfile
 */
public class PetProfileFetcherRequestModel {
    private final String petId;

    public PetProfileFetcherRequestModel(String petId) {
        this.petId = petId;
    }

    public String getPetId() {
        return petId;
    }
}
