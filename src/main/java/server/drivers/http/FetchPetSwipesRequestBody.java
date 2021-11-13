package server.drivers.http;

/**
 * Represents an HTTP request body for the "/pets/fetchswipes" POST route.
 */
public class FetchPetSwipesRequestBody {

    private int petId;

    public FetchPetSwipesRequestBody(int petId) {
        this.petId = petId;
    }

    public int getPetId() {
        return this.petId;
    }

}
