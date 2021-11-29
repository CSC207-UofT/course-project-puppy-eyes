package server.use_cases.pet_use_cases.pet_profile_image_changer;

import server.drivers.IImageService;
import server.drivers.ImageUploadResponse;
import server.use_cases.ResponseModel;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.repo_abstracts.IImageRepository;

public class PetProfileImageChanger implements PetProfileImageChangerInputBoundary {

    private final IImageService imageService;
    private final IImageRepository imageRepository;
    private final PetActionValidatorInputBoundary petActionValidator;

    public PetProfileImageChanger(IImageRepository imageRepository, IImageService imageService,
                                  PetActionValidatorInputBoundary petActionValidator) {
        this.imageRepository = imageRepository;
        this.petActionValidator = petActionValidator;
        this.imageService = imageService;
    }

    @Override
    public ResponseModel changeProfileImage(PetProfileImageChangerRequestModel request) {
        ResponseModel validateActionResponse = petActionValidator.validateAction(new PetActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getPetId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        // Fetch the response from uploading the base64 image
        ImageUploadResponse uploadResponse = imageService.uploadBase64(request.getBase64Encoded());

        if (uploadResponse == null) {
            return new ResponseModel(false, "An error occurred while trying to upload this image.");
        }

        String url = uploadResponse.getUrl();
        String assetId = uploadResponse.getAssetId();

        boolean isSuccess = imageRepository.setPetProfileImage(Integer.parseInt(request.getPetId()), assetId, url);

        if (!isSuccess) {
            return new ResponseModel(false, "An unexpected error occurred.");
        }

        return new ResponseModel(true, "Successfully changed the pet's profile image.",
                new PetProfileImageChangerResponseModel(assetId, url));
    }

}
