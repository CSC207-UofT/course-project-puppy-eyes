package server.use_cases;

import java.util.List;

/**
 * An object defining the request type for
 * PetMatchesFetcherInputBoundary.fetchPetMatches
 */
public class PetMatchesFetcherResponseModel {

    private final boolean isSuccess;
    private final List<String> petIds;

    public PetMatchesFetcherResponseModel(boolean isSuccess, List<String> petIds) {
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
