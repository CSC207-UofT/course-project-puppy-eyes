package server.use_cases.pet_use_cases.pet_swipes_fetcher;

import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.pet_use_cases.pet_matches_fetcher.PetMatchesFetcherResponseModel;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.ResponseModel;

import java.util.List;
import java.util.stream.Collectors;

public class PetSwipesFetcher implements PetSwipesFetcherInputBoundary {

    private final IPetRepository petRepository;
    private final PetActionValidatorInputBoundary petActionValidator;

    public PetSwipesFetcher(IPetRepository petRepository, PetActionValidatorInputBoundary petActionValidator) {
        this.petRepository = petRepository;
        this.petActionValidator = petActionValidator;
    }

    /**
     * Create a new PetSwipesFetcher with given request.
     *
     * @param request Object containing id of the pet.
     * @return a PetSwipesFetcherResponseModel that a list of pet ids that the pet has swiped on.
     */
    @Override
    public ResponseModel fetchPetSwipes(PetSwipesFetcherRequestModel request)  {
        ResponseModel validateActionResponse = petActionValidator.validateAction(new PetActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getPetId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int petId = Integer.parseInt(request.getPetId());

        List<String> stringPetIds = petRepository.fetchSwipedOn(petId).stream().
                map(String::valueOf).
                collect(Collectors.toList());

        return new ResponseModel(
                true,
                "Successfully retrieved pet swipes.",
                new PetSwipesFetcherResponseModel(stringPetIds)
        );
    }

}
