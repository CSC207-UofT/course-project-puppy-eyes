package server.drivers.http.requestBody;

/**
 * Represents an HTTP request body for the "/pets/swipe" POST route.
 */
public class SwipePetsRequestBody {

    private int pet1Id;
    private int pet2Id;

    public SwipePetsRequestBody(int pet1Id, int pet2Id) {
        this.pet1Id = pet1Id;
        this.pet2Id = pet2Id;
    }

    public int getFirstPetId() {
        return this.pet1Id;
    }

    public int getSecondPetId() {
        return this.pet2Id;
    }

}
