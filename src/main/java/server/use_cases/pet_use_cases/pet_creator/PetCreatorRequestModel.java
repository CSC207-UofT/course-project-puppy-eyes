package server.use_cases.pet_use_cases.pet_creator;

import server.use_cases.AuthRequestModel;

/**
 * An object defining the request type for PetCreator.createPet
 */
public class PetCreatorRequestModel extends AuthRequestModel {

    private final String name;
    private final String age;
    private final String breed;
    private final String biography;

    public PetCreatorRequestModel(String headerUserId, String userId, String name, String age, String breed, String biography) {
        super(headerUserId, userId);
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public String getBiography() {
        return biography;
    }
}
