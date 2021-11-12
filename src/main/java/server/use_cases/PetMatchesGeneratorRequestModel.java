package server.use_cases;

import server.entities.Pet;
import server.entities.User;

/**
 * An object defining the request type for PetMatchesGenerator.generatePotentialMatches
 */
public class PetMatchesGeneratorRequestModel {

    private Pet pet;
    private User user;

    public PetMatchesGeneratorRequestModel(Pet pet, User user) {
        this.pet = pet;
        this.user = user;
    }

    public Pet getPet() {
        return this.pet;
    }

    public User getUser() {
        return this.user;
    }

}
