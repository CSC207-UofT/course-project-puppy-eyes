package server.drivers.http;

/**
 * Represents an HTTP request body for the "/pets/fetchmatches" POST route.
 */
public class FetchPetMatchesRequestBody {

    private int petId;

    public FetchPetMatchesRequestBody(int petId) {
        this.petId = petId;
    }

    public int getPetId() {
        return this.petId;
    }

}
