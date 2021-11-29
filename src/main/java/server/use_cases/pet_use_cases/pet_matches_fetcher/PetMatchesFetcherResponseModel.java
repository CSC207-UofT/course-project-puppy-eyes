package server.use_cases.pet_use_cases.pet_matches_fetcher;

import server.use_cases.ResponseData;

import java.util.List;

/**
 * An object defining the request type for
 * PetMatchesFetcherInputBoundary.fetchPetMatches
 */
public class PetMatchesFetcherResponseModel extends ResponseData {

    private final List<String> petIds;

    public PetMatchesFetcherResponseModel(List<String> petIds) {
        this.petIds = petIds;
    }

    public List<String> getPetIds() {
        return petIds;
    }
}
