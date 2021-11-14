package server.use_cases;

import server.use_cases.repo_abstracts.AuthRequestModel;

/**
 * An object defining the request type for PetMatchesGenerator.generatePotentialMatches
 */
public class PetMatchesGeneratorRequestModel extends AuthRequestModel {

    private String petId;

    public PetMatchesGeneratorRequestModel(String headerUserId, String userId, String petId) {
        super(headerUserId, userId);
        this.petId = petId;
    }

    public String getPet() {
        return this.petId;
    }

}
