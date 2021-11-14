package server.use_cases;

import server.use_cases.repo_abstracts.ResponseData;

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
