package server.use_cases;

/**
 * An object defining the request type for PetEditor.editPet
 */
public class PetEditorRequestModel {
    private final String newName;
    private final int newAge;
    private String newBreed;
    private String newBiography;
    private final String petId;

    public PetEditorRequestModel(String petId, String newName, int newAge, String newBreed, String newBiography) {
        this.petId = petId;
        this.newName = newName;
        this.newAge = newAge;
        this.newBreed = newBreed;
        this.newBiography = newBiography;
    }

    public String getPetId() {
        return petId;
    }

    public String getNewName() {
        return newName;
    }

    public int getNewAge() {
        return newAge;
    }

    public String getNewBreed() {
        return newBreed;
    }

    public String getNewBiography() {
        return newBiography;
    }
}
