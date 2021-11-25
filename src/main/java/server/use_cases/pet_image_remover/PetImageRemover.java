package server.use_cases.pet_image_remover;

import server.drivers.IImageService;
import server.entities.Pet;
import server.use_cases.ResponseModel;
import server.use_cases.repo_abstracts.IImageRepository;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;

import java.util.regex.Pattern;

public class PetImageRemover implements PetImageRemoverInputBoundary {

    private final IImageService imageService;
    private final IImageRepository imageRepository;
    private final IPetRepository petRepository;

    public PetImageRemover(IImageRepository imageRepository, IPetRepository petRepository, IImageService imageService) {
        this.imageRepository = imageRepository;
        this.petRepository = petRepository;
        this.imageService = imageService;
    }

    @Override
    public ResponseModel removeImage(PetImageRemoverRequestModel request) {
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
            return new ResponseModel();
        }

        boolean isSuccess = imageRepository.deletePetImage(petId, request.getAssetId());

        if (!isSuccess) {
            return new ResponseModel(false, "An unexpected error occurred.");
        }

        return new ResponseModel(true, "Successfully removed the image.");

    }

}
