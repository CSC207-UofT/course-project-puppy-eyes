package server.use_cases.pet_interactor;

import server.use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.ResponseModel;

/**
 * A use case responsible for handling a pet "rejecting" another pet.
 */
public class PetRejector implements PetRejectorInputBoundary {

    private final IPetRepository petRepository;
    private final PetActionValidatorInputBoundary petActionValidator;

    public PetRejector(IPetRepository petRepository, PetActionValidatorInputBoundary petActionValidator) {
        this.petRepository = petRepository;
        this.petActionValidator = petActionValidator;
    }

    @Override
    public ResponseModel rejectPets(PetRejectorRequestModel request) {
        ResponseModel validateActionResponse = petActionValidator.validateAction(new PetActionValidatorRequestModel(
                request.getHeaderUserId(), request.getFirstPetId(), request.getSecondPetId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int pet1Id = Integer.parseInt(request.getFirstPetId());
        int pet2Id = Integer.parseInt(request.getSecondPetId());

        // Do nothing if the pets are already matched
        if (this.petRepository.fetchMatches(pet1Id).contains(pet2Id)) {
            new ResponseModel(false, "Both pets are already matched with each other.");
        }

        // Remove both pets from each other's swiped list
        this.petRepository.unswipePets(pet1Id, pet2Id);
        this.petRepository.unswipePets(pet2Id, pet1Id);

        // Add the second pet to the first pet's rejected list
        this.petRepository.rejectPets(pet1Id, pet2Id);
        return new ResponseModel(false, "Successfully rejected pet2 from pet1.");
    }

}