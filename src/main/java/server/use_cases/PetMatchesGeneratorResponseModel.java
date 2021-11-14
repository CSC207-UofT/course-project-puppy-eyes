package server.use_cases;

import server.entities.Pet;

import java.util.List;

/**
 * An object defining the response type for PetMatchesGenerator.generatePotentialMatches
 */
public class PetMatchesGeneratorResponseModel {

    private final List<String> potentialMatches;

    public PetMatchesGeneratorResponseModel(List<String> potentialMatches) {
        this.potentialMatches = potentialMatches;
    }

    public List<String> getPotentialMatches() {
        return this.potentialMatches;
    }

}
