package server.use_cases.pet_image_adder;

import server.drivers.IImageService;
import server.drivers.ImageUploadResponse;
import server.use_cases.ResponseModel;
import server.use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.pet_profile_image_changer.PetProfileImageChangerResponseModel;
import server.use_cases.repo_abstracts.IImageRepository;
import server.use_cases.repo_abstracts.IPetRepository;

public class PetImageAdder implements PetImageAdderInputBoundary {

    private final IImageService imageService;
    private final IImageRepository imageRepository;
    private final IPetRepository petRepository;
    private final PetActionValidatorInputBoundary petActionValidator;

    public PetImageAdder(IImageRepository imageRepository,
                         IPetRepository petRepository,
                         IImageService imageService,
                         PetActionValidatorInputBoundary petActionValidator) {
        this.imageRepository = imageRepository;
        this.petRepository = petRepository;
        this.imageService = imageService;
        this.petActionValidator = petActionValidator;
    }

    @Override
    public ResponseModel addImage(PetImageAdderRequestModel request) {
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

        int petId = Integer.parseInt(request.getPetId());
        String url = uploadResponse.getUrl();
        String assetId = uploadResponse.getAssetId();

        boolean isSuccess = imageRepository.addPetImage(petId, assetId, url);

        if (!isSuccess) {
            return new ResponseModel(false, "An unexpected error occurred.");
        }

        return new ResponseModel(true, "Successfully added image to the pet's image gallery.",
                new PetProfileImageChangerResponseModel(assetId, url));
    }

}
