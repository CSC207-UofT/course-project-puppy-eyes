package server.use_cases.pet_editor;

import server.use_cases.ResponseData;

import java.util.Objects;

/**
 * An object defining the response type for PetEditor.editPet.
 */
public class PetEditorResponseModel extends ResponseData {

    private final String name;
    private final int age;
    private String breed;
    private String biography;
    private final String petId;

    public PetEditorResponseModel(String name, int age, String breed, String biography, String petId) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.biography = biography;
        this.petId = petId;
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
        return  Objects.equals(name, that.name)
                && Objects.equals(age, that.age)
                && Objects.equals(breed, that.breed) &&
                Objects.equals(biography, that.biography);
    }
}
