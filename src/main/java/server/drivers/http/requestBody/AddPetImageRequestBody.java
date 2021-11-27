package server.drivers.http.requestBody;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an HTTP request body for the "/pets/addimage" POST route.
 */
public class AddPetImageRequestBody {

    private final String base64Encoded, petId;

    public AddPetImageRequestBody(String petId, @JsonProperty("base64Encoded") String base64Encoded) {
        this.base64Encoded = base64Encoded;
        this.petId = petId;
    }

    public String getPetId() {
        return this.petId;
    }

    public String getBase64Encoded() {
        return this.base64Encoded;
    }

}
