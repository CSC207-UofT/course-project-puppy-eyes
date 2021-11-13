package server.use_cases;

import java.util.List;

/**
 * An object defining the request type for
 * PetSwipesFetcherInputBoundary.fetchPetSwipes
 */
public class PetSwipesFetcherResponseModel {

    private final boolean isSuccess;
    private final List<String> petIds;

    public PetSwipesFetcherResponseModel(boolean isSuccess, List<String> petIds) {
        this.isSuccess = isSuccess;
        this.petIds = petIds;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public List<String> getPetIds() {
        return petIds;
    }
}
