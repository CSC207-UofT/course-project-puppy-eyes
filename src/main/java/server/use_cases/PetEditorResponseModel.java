package server.use_cases;

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

    public PetEditorResponseModel(boolean isSuccess, String name, int age, String breed, String biography, String petId) {
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
}
