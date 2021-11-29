package server.drivers.http.requestBody;

/**
 * Represents an HTTP request body for the "/pets/unmatch" POST route.
 */
public class UnmatchPetsRequestBody {

    private String pet1Id;
    private String pet2Id;

    public UnmatchPetsRequestBody(String pet1Id, String pet2Id) {
        this.pet1Id = pet1Id;
        this.pet2Id = pet2Id;
    }

    public String getFirstPetId() {
        return this.pet1Id;
    }

    public String getSecondPetId() {
        return this.pet2Id;
    }

}
