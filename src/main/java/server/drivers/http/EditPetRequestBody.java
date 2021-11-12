package server.drivers.http;

/**
 * Represents an HTTP request body for the "/pets/edit" POST route.
 */
public class EditPetRequestBody {
    private final String petId;
    private final String newName;
    private final int newAge;
    private final String newBreed;
    private final String newBiography;

    public EditPetRequestBody(String petId, String newName, int newAge, String newBreed, String newBiography) {
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
