package server.use_cases.pet_profile_image_changer;

import server.use_cases.AuthRequestModel;

public class PetProfileImageChangerRequestModel extends AuthRequestModel {

    private final String base64Encoded, petId;

    /**
     * Create a request to upload an image to set as the pet's new profile picture.
     * @param headerUserId      the id of the user passed from the request header
     * @param petId             the id of the pet
     * @param base64Encoded     the Base64 encoded image as a String
     */
    public PetProfileImageChangerRequestModel(String headerUserId, String petId, String base64Encoded) {
        super(headerUserId);
        this.base64Encoded = base64Encoded;
        this.petId = petId;
    }

    public String getBase64Encoded() {
        return this.base64Encoded;
    }

    public String getPetId() {
        return this.petId;
    }

}
