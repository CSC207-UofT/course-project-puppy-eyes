package server.use_cases;

import server.use_cases.repo_abstracts.AuthRequestModel;

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
