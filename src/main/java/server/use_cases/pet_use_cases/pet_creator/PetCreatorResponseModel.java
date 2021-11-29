package server.use_cases.pet_use_cases.pet_creator;

import server.use_cases.ResponseData;

import java.util.Objects;

/**
 * An object defining the response type for PetCreator.createPet.
 */
public class PetCreatorResponseModel extends ResponseData {

    private final String name;
    private final String age;
    private final String breed;
    private final String biography;
    private final String petId;
    private final String userId;

    public PetCreatorResponseModel(String petId, String userId, String name, String age, String breed, String biography) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.biography = biography;
        this.petId = petId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            PetCreatorResponseModel that = (PetCreatorResponseModel) o;
            return
                    Objects.equals(name, that.name)
                    && Objects.equals(age, that.age)
                    && Objects.equals(userId, that.userId)
                    && Objects.equals(petId, that.petId);
        }
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPetId() {
        return petId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getBreed() {
        return breed;
    }

    public String getBiography() {
        return biography;
    }
}
