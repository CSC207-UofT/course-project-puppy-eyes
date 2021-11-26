package server.use_cases.pet_use_cases.pet_editor;

import server.use_cases.AuthRequestModel;

/**
 * An object defining the request type for PetEditor.editPet
 */
public class PetEditorRequestModel extends AuthRequestModel {

    private final String newName;
    private final String newAge;
    private final String newBreed;
    private final String newBiography;
    private final String petId;

    public PetEditorRequestModel(String headerUserId, String petId, String newName, String newAge, String newBreed, String newBiography) {
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

    public String getNewAge() {
        return newAge;
    }

    public String getNewBreed() {
        return newBreed;
    }

    public String getNewBiography() {
        return newBiography;
    }
}
