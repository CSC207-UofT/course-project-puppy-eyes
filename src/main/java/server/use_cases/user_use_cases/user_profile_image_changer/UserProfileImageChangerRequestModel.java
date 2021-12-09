package server.use_cases.user_use_cases.user_profile_image_changer;

import server.use_cases.AuthRequestModel;

public class UserProfileImageChangerRequestModel extends AuthRequestModel {

    private final String base64Encoded;

    /**
     * Create a request to upload an image to set as the user's new profile picture.
     *
     * @param headerUserId  the userId passed from the request header
     * @param base64Encoded the Base64 encoded image as a String
     */
    public UserProfileImageChangerRequestModel(String headerUserId, String base64Encoded) {
        super(headerUserId);
        this.base64Encoded = base64Encoded;
    }

    public String getBase64Encoded() {
        return this.base64Encoded;
    }

}
