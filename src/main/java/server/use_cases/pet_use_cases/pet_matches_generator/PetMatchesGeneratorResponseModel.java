package server.use_cases.pet_use_cases.pet_matches_generator;

import server.use_cases.ResponseData;

import java.util.List;

/**
 * An object defining the response type for PetMatchesGenerator.generatePotentialMatches
 */
public class PetMatchesGeneratorResponseModel extends ResponseData {

    private final List<String> petIds;

    public PetMatchesGeneratorResponseModel(List<String> potentialMatches) {
        this.petIds = potentialMatches;
    }

    public List<String> getPetIds() {
        return this.petIds;
    }

}
