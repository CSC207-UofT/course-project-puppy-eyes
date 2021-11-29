package server.use_cases.pet_use_cases.pet_gallery_images_fetcher;

import server.use_cases.ResponseModel;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.repo_abstracts.IImageRepository;

public class PetGalleryImagesFetcher implements PetGalleryImagesFetcherInputBoundary {

    private final IImageRepository imageRepository;
    private final PetActionValidatorInputBoundary petActionValidator;

    public PetGalleryImagesFetcher(IImageRepository imageRepository, PetActionValidatorInputBoundary petActionValidator) {
        this.imageRepository = imageRepository;
        this.petActionValidator = petActionValidator;
    }

    @Override
    public ResponseModel fetchImages(PetGalleryImagesFetcherRequestModel request) {
        ResponseModel validateActionResponse = petActionValidator.validateAction(new PetActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getPetId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int petId = Integer.parseInt(request.getPetId());

        return new ResponseModel(true, "Successfully fetched pet gallery images.",
                new PetGalleryImagesFetcherResponseModel(imageRepository.fetchPetImagesLink(petId)));
    }

}
