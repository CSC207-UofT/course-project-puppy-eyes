package server.drivers.http.requestBody;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an HTTP request body for the "/users/setprofileimage" POST route.
 */
public class SetUserProfileImageRequestBody {

    private final String base64Encoded;

    public SetUserProfileImageRequestBody(@JsonProperty("base64Encoded") String base64Encoded) {
        this.base64Encoded = base64Encoded;
    }

    public String getBase64Encoded() {
        return this.base64Encoded;
    }

}
