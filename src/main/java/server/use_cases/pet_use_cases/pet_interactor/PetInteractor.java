package server.use_cases.pet_use_cases.pet_interactor;

import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.pet_use_cases.pet_interactor.interactions.*;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.ResponseModel;

/**
 * A use case responsible for handling an interaction between two pets.
 */
public class PetInteractor implements PetInteractorInputBoundary {

    private final IPetRepository petRepository;
    private final PetActionValidatorInputBoundary petActionValidator;
    private final PetInteractionContext petInteractionContext;
    private final PetInteraction petSwiper, petUnswiper, petRejector, petUnmatcher;

    public PetInteractor(IPetRepository petRepository, PetActionValidatorInputBoundary petActionValidator) {
        this.petRepository = petRepository;
        this.petActionValidator = petActionValidator;

        // Initialize context
        this.petInteractionContext = new PetInteractionContext();

        // Initialize strategies
        this.petSwiper = new PetSwiper(petRepository);
        this.petUnswiper = new PetUnswiper(petRepository);
        this.petRejector = new PetRejector(petRepository);
        this.petUnmatcher = new PetUnmatcher(petRepository);
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

        // Supply the context with a strategy based on the request model
        switch (request.getInteractionType()) {
            case SWIPE:
                petInteractionContext.setInteraction(petSwiper);
                break;
            case UNSWIPE:
                petInteractionContext.setInteraction(petUnswiper);
                break;
            case REJECT:
                petInteractionContext.setInteraction(petRejector);
                break;
            case UNMATCH:
                petInteractionContext.setInteraction(petUnmatcher);
                break;
            default:
                return new ResponseModel(false, "An unexpected error occurred.");
        }

        // execute the strategy and return the ResponseModel
        return petInteractionContext.interact(pet1Id, pet2Id);
    }

}