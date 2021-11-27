package server.drivers.http.requestBody;

/**
 * Represents an HTTP request body for the "/pets/removeimage" POST route.
 */
public class RemovePetImageRequestBody {

    private final String assetId, petId;

    public RemovePetImageRequestBody(String petId, String assetId) {
        this.assetId = assetId;
        this.petId = petId;
    }

    public String getPetId() {
        return this.petId;
    }

    public String getAssetId() {
        return this.assetId;
    }

}
