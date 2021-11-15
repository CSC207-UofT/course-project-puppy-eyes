package server.use_cases;

import server.use_cases.repo_abstracts.AuthRequestModel;

/**
 * An object defining the request type for PetEditor.editPet
 */
public class PetEditorRequestModel extends AuthRequestModel {

    private final String newName;
    private final int newAge;
    private String newBreed;
    private String newBiography;
    private String petId;

    public PetEditorRequestModel(String headerUserId, String petId, String newName, int newAge, String newBreed, String newBiography) {
        super(headerUserId);
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
