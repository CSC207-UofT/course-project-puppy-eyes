package server.use_cases.pet_use_cases.pet_gallery_images_fetcher;

import server.use_cases.AuthRequestModel;

public class PetGalleryImagesFetcherRequestModel extends AuthRequestModel {

    private final String petId;

    public PetGalleryImagesFetcherRequestModel(String headerUserId, String petId) {
        super(headerUserId);
        this.petId = petId;
    }

    public String getPetId() {
        return petId;
    }

}
