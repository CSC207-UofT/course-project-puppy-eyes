package server.use_cases.pet_action_validator;

import server.entities.Pet;
import server.use_cases.ResponseModel;
import server.use_cases.Util;
import server.use_cases.repo_abstracts.IPetRepository;

public class PetActionValidator implements PetActionValidatorInputBoundary {

    private IPetRepository petRepository;

    public PetActionValidator(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public ResponseModel validateAction(PetActionValidatorRequestModel request) {
        // null checks
        if (request.getPetId() == null || (!request.isFromTerminal() && request.getHeaderUserId() == null)) {
            return new ResponseModel(false, "Missing required fields.");
        }

        // Check if the request fields are in the valid datatype
        if (!Util.isInteger(request.getPetId())
                || (!request.isFromTerminal() && !Util.isInteger(request.getHeaderUserId()))
                || (request.getSecondPetId() != null && !Util.isInteger(request.getSecondPetId()))) {
            return new ResponseModel(false, "ID must be an integer.");
        }

        int petId = Integer.parseInt(request.getPetId());
        Pet pet = petRepository.fetchPet(petId);

        if (pet == null) {
            return new ResponseModel(false, "Pet with ID: " + request.getPetId() + " does not exist.");
        }

        if (request.getSecondPetId() != null && petRepository.fetchPet(Integer.parseInt(request.getSecondPetId())) == null) {
            return new ResponseModel(false, "Pet with ID: " + request.getSecondPetId() + " does not exist.");
        }

        // Set the userId in the request object to allow for checking authorization
        request.setUserId(String.valueOf(pet.getUserId()));

        if (!request.isRequestAuthorized()) {
            return new ResponseModel();
        }

        return new ResponseModel(true, "Action validated.");
    }

}
