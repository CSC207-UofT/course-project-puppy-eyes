package server.drivers.http.requestBody;

/**
 * Represents an HTTP request body for the "/pets/edit" POST route.
 */
public class EditPetRequestBody {
    private final String petId;
    private final String newName;
    private final String newAge;
    private final String newBreed;
    private final String newBiography;

    public EditPetRequestBody(String petId, String newName, String newAge, String newBreed, String newBiography) {
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
