package server.use_cases.pet_use_cases.pet_image_remover;

import server.use_cases.AuthRequestModel;

public class PetImageRemoverRequestModel extends AuthRequestModel {

    private final String assetId, petId;

    /**
     * Create a request to delete an image from the pet's image gallery.
     * @param headerUserId      the id of the user passed from the request header
     * @param petId             the id of the pet
     * @param assetId           the id of the image
     */
    public PetImageRemoverRequestModel(String headerUserId, String petId, String assetId) {
        super(headerUserId);
        this.assetId = assetId;
        this.petId = petId;
    }

    public String getAssetId() {
        return this.assetId;
    }

    public String getPetId() {
        return this.petId;
    }

}
