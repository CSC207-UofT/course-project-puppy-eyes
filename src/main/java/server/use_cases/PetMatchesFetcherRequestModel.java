package server.use_cases;

/**
 * An object defining the request type for
 * PetMatchesFetcherInputBoundary.fetchPetMatches
 */
public class PetMatchesFetcherRequestModel {
    private final String petId;

    public PetMatchesFetcherRequestModel(String petId) {
        this.petId = petId;
    }

    public String getPetId() {
        return petId;
    }
}
