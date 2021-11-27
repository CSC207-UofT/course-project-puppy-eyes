package server.drivers.http.requestBody;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an HTTP request body for the "/pets/setprofileimage" POST route.
 */
public class SetPetProfileImageRequestBody {

    private final String base64Encoded, petId;

    public SetPetProfileImageRequestBody(String petId, @JsonProperty("base64Encoded") String base64Encoded) {
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
