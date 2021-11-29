package server.use_cases.pet_use_cases.pet_swipes_fetcher;

import java.util.List;

/**
 * An object defining the request type for
 * PetSwipesFetcherInputBoundary.fetchPetSwipes
 */
public class PetSwipesFetcherResponseModel {

    private final List<String> petIds;

    public PetSwipesFetcherResponseModel(List<String> petIds) {
        this.petIds = petIds;
    }

    public List<String> getPetIds() {
        return petIds;
    }
}
