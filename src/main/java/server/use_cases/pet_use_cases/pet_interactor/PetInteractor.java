package server.use_cases.pet_use_cases.pet_interactor;

import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.ResponseModel;

/**
 * A use case responsible for handling a pet "rejecting" another pet.
 */
public class PetInteractor implements PetInteractorInputBoundary {

    private final IPetRepository petRepository;
    private final PetActionValidatorInputBoundary petActionValidator;

    public PetInteractor(IPetRepository petRepository, PetActionValidatorInputBoundary petActionValidator) {
        this.petRepository = petRepository;
        this.petActionValidator = petActionValidator;
    }

    @Override
    public ResponseModel interact(PetInteractorRequestModel request) {
        ResponseModel validateActionResponse = petActionValidator.validateAction(new PetActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getFirstPetId(), request.getSecondPetId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int pet1Id = Integer.parseInt(request.getFirstPetId());
        int pet2Id = Integer.parseInt(request.getSecondPetId());

        switch (request.getInteractionType()) {
            case SWIPE:
                return swipe(pet1Id, pet2Id);
            case UNSWIPE:
                return unswipe(pet1Id, pet2Id);
            case REJECT:
                return reject(pet1Id, pet2Id);
            case UNMATCH:
                return unmatch(pet1Id, pet2Id);
            default:
                return new ResponseModel(false, "An unexpected error occurred.");
        }
    }

    private ResponseModel swipe(int pet1Id, int pet2Id) {
        // If the first pet already swiped on the second pet, do nothing.
        if (petRepository.fetchSwipedOn(pet1Id).contains(pet2Id)) {
            return new ResponseModel(false, "The first pet is already matched with the second pet.");
        }

        // If the second pet already swiped on the first pet,
        // remove the first pet from the second pet's swiped list and match both of them
        if (petRepository.fetchSwipedOn(pet2Id).contains(pet1Id)) {
            this.petRepository.unswipePets(pet1Id, pet2Id);
            this.petRepository.unswipePets(pet2Id, pet1Id);
            this.petRepository.matchPets(pet1Id, pet2Id);
            return new ResponseModel(true, "Successfully swiped and matched both pets.");
        }

        // If neither pet swiped on each other,
        // add the second pet to the first pet's swiped list
        this.petRepository.swipePets(pet1Id, pet2Id);
        return new ResponseModel(true, "Successfully swiped pet2 from pet1.");
    }

    private ResponseModel reject(int pet1Id, int pet2Id) {
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

    private ResponseModel unswipe(int pet1Id, int pet2Id) {
        // If the first pet already swiped on the second pet, remove the second pet from the first pet's swiped list
        if (this.petRepository.fetchSwipedOn(pet1Id).contains(pet2Id)) {
            this.petRepository.unswipePets(pet1Id, pet2Id);
            return new ResponseModel(true, "Successfully removed pet2 from pet1's swiped list.");
        }

        return new ResponseModel(false, "Pet2 is not in pet1's swiped list.");
    }

    private ResponseModel unmatch(int pet1Id, int pet2Id) {
        if (this.petRepository.fetchMatches(pet1Id).contains(pet2Id) && this.petRepository.fetchMatches(pet2Id).contains(pet1Id)) {
            this.petRepository.unmatchPets(pet1Id, pet2Id);
            return new ResponseModel(false, "Successfully unmatched pet1 and pet2.");
        }

        return new ResponseModel(false, "These pets are not matched.");
    }

}