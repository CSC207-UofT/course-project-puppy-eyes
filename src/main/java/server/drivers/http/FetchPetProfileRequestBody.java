package server.drivers.http;

/**
 * Represents an HTTP request body for the "/pets/profile" GET route.
 */
public class FetchPetProfileRequestBody {
    private final String petId;

    public FetchPetProfileRequestBody(String petId) {
        this.petId = petId;
    }

    public String getPetId() {
        return petId;
    }
}
