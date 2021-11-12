package server.use_cases;

import server.entities.Pet;

import java.util.List;

/**
 * An object defining the response type for PetMatchesGenerator.generatePotentialMatches
 */
public class PetMatchesGeneratorResponseModel {

    private final boolean isSuccess;
    private final List<Pet> potentialMatches;

    public PetMatchesGeneratorResponseModel(boolean isSuccess, List<Pet> potentialMatches) {
        this.isSuccess = isSuccess;
        this.potentialMatches = potentialMatches;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public List<Pet> getPotentialMatches() {
        return this.potentialMatches;
    }

}
