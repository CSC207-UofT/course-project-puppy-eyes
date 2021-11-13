package server.use_cases;

/**
 * An object defining the request type for
 * PetSwipesFetcherInputBoundary.fetchPetSwipes
 */
public class PetSwipesFetcherRequestModel {
    private final String petId;

    public PetSwipesFetcherRequestModel(String petId) {
        this.petId = petId;
    }

    public String getPetId() {
        return petId;
    }
}
