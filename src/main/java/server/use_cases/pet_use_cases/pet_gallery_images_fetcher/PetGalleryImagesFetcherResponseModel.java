package server.use_cases.pet_use_cases.pet_gallery_images_fetcher;

import server.use_cases.ResponseData;

import java.util.List;

public class PetGalleryImagesFetcherResponseModel extends ResponseData {

    private final List<String> urls;

    public PetGalleryImagesFetcherResponseModel(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getUrls() {
        return this.urls;
    }

}
