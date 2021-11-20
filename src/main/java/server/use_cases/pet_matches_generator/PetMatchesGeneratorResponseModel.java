package server.use_cases.pet_matches_generator;

import server.use_cases.repo_abstracts.ResponseData;

import java.util.List;

/**
 * An object defining the response type for PetMatchesGenerator.generatePotentialMatches
 */
public class PetMatchesGeneratorResponseModel extends ResponseData {

    private final List<String> potentialMatches;

    public PetMatchesGeneratorResponseModel(List<String> potentialMatches) {
        this.potentialMatches = potentialMatches;
    }

    public List<String> getPotentialMatches() {
        return this.potentialMatches;
    }

}
