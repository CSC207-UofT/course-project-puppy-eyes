package server.use_cases.pet_use_cases.pet_matches_generator;

import server.use_cases.AuthRequestModel;

/**
 * An object defining the request type for PetMatchesGenerator.generatePotentialMatches
 */
public class PetMatchesGeneratorRequestModel extends AuthRequestModel {

    private String petId;

    public PetMatchesGeneratorRequestModel(String headerUserId, String petId) {
        super(headerUserId);
        this.petId = petId;
    }

    public String getPetId() {
        return this.petId;
    }

}
