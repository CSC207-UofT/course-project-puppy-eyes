package server.use_cases.pet_use_cases.pet_image_remover;

import server.drivers.IImageService;
import server.use_cases.ResponseModel;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.repo_abstracts.IImageRepository;
import server.use_cases.repo_abstracts.IPetRepository;

public class PetImageRemover implements PetImageRemoverInputBoundary {

    private final IImageService imageService;
    private final IImageRepository imageRepository;
    private final IPetRepository petRepository;
    private final PetActionValidatorInputBoundary petActionValidator;

    public PetImageRemover(IImageRepository imageRepository,
                           IPetRepository petRepository,
                           IImageService imageService,
                           PetActionValidatorInputBoundary petActionValidator) {
        this.imageRepository = imageRepository;
        this.petRepository = petRepository;
        this.imageService = imageService;
        this.petActionValidator = petActionValidator;
    }

    @Override
    public ResponseModel removeImage(PetImageRemoverRequestModel request) {
        ResponseModel validateActionResponse = petActionValidator.validateAction(new PetActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getPetId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int petId = Integer.parseInt(request.getPetId());

        boolean isSuccess = imageRepository.deletePetImage(petId, request.getAssetId());

        if (!isSuccess) {
            return new ResponseModel(false, "An unexpected error occurred.");
        }

        return new ResponseModel(true, "Successfully removed the image.");

    }

}
