package server.use_cases;

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
