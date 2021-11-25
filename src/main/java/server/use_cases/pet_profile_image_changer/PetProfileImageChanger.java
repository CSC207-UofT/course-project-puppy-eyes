package server.use_cases.pet_profile_image_changer;

import server.drivers.IImageService;
import server.drivers.ImageUploadResponse;
import server.entities.Pet;
import server.use_cases.ResponseModel;
import server.use_cases.repo_abstracts.IImageRepository;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;

import java.util.Map;
import java.util.regex.Pattern;

public class PetProfileImageChanger implements PetProfileImageChangerInputBoundary {

    private final IImageService imageService;
    private final IImageRepository imageRepository;
    private final IPetRepository petRepository;

    public PetProfileImageChanger(IImageRepository imageRepository, IPetRepository petRepository, IImageService imageService) {
        this.imageRepository = imageRepository;
        this.petRepository = petRepository;
        this.imageService = imageService;
    }

    @Override
    public ResponseModel changeProfileImage(PetProfileImageChangerRequestModel request) {
        String intRegex = "-?[0-9]+";
        Pattern intPattern = Pattern.compile(intRegex);

        // null checks
        if (request.getPetId() == null || request.getHeaderUserId() == null) {
            return new ResponseModel(false, "Missing required fields.");
        }

        if (!intPattern.matcher(request.getPetId()).matches() || !intPattern.matcher(request.getHeaderUserId()).matches()) {
            return new ResponseModel(false, "ID must be an integer.");
        }

        int petId = Integer.parseInt(request.getPetId());
        int userId = Integer.parseInt(request.getHeaderUserId());
        Pet pet;

        try {
            pet = petRepository.fetchPet(petId);
        } catch (PetNotFoundException exception) {
            return new ResponseModel(false, "Pet with ID: " + petId + " does not exist.");
        }

        // userHeaderId must match this pet's owner's userId
        if (pet.getUserId() != userId) {
            return new ResponseModel(false, "You are not authorized to make this request.");
        }

        // Fetch the response from uploading the base64 image
        ImageUploadResponse uploadResponse = imageService.uploadBase64(request.getBase64Encoded());

        if (uploadResponse == null) {
            return new ResponseModel(false, "An error occurred while trying to upload this image.");
        }

        String url = uploadResponse.getUrl();
        String assetId = uploadResponse.getAssetId();

        boolean isSuccess = imageRepository.setPetProfileImage(petId, assetId, url);

        if (!isSuccess) {
            return new ResponseModel(false, "An unexpected error occurred.");
        }

        return new ResponseModel(true, "Successfully changed the pet's profile image.",
                new PetProfileImageChangerResponseModel(assetId, url));
    }

}
