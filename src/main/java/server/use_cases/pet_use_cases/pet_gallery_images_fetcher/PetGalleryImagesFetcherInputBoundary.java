package server.use_cases.pet_use_cases.pet_gallery_images_fetcher;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the PetGalleryImagesFetcher use case.
 */
public interface PetGalleryImagesFetcherInputBoundary {

    public ResponseModel fetchImages(PetGalleryImagesFetcherRequestModel request);

}
