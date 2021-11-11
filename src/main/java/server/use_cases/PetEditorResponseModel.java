package server.use_cases;

import java.util.Objects;

/**
 * An object defining the response type for PetEditor.editPet.
 */
public class PetEditorResponseModel {
    private final boolean isSuccess;
    private final String name;
    private final int age;
    private String breed;
    private String biography;
    private final String petId;

    public PetEditorResponseModel(boolean isSuccess, String name, int age, String breed,
                                  String biography, String petId) {
        this.isSuccess = isSuccess;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.biography = biography;
        this.petId = petId;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public String getBiography() {
        return biography;
    }

    public String getPetId() {
        return petId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetEditorResponseModel that = (PetEditorResponseModel) o;
        return isSuccess == that.isSuccess
                && Objects.equals(name, that.name)
                && Objects.equals(age, that.age)
                && Objects.equals(breed, that.breed) &&
                Objects.equals(biography, that.biography);
    }
}
