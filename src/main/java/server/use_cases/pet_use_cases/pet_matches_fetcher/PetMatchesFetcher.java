package server.use_cases.pet_use_cases.pet_matches_fetcher;

import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.ResponseModel;

import java.util.List;
import java.util.stream.Collectors;

public class PetMatchesFetcher implements PetMatchesFetcherInputBoundary {
    private final IPetRepository petRepository;
    private final PetActionValidatorInputBoundary petActionValidator;

    public PetMatchesFetcher(IPetRepository petRepository, PetActionValidatorInputBoundary petActionValidator) {
        this.petRepository = petRepository;
        this.petActionValidator = petActionValidator;
    }

    /**
     * Create a new ResponseModel with ResponseData of PetMatchesFetcherResponse for the given request.
     *
     * @param request Object containing id of the pet.
     * @return a PetMatchesFetcherResponseModel that a list of pet ids that the pet has matched with.
     */
    @Override
    public ResponseModel fetchPetMatches(PetMatchesFetcherRequestModel request)  {
        ResponseModel validateActionResponse = petActionValidator.validateAction(new PetActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getPetId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int id = Integer.parseInt(request.getPetId());

        List<String> stringPetIds = petRepository.fetchMatches(id).
                stream().
                map(String::valueOf).
                collect(Collectors.toList());

        return new ResponseModel(
            true,
            "Successfully retrieved pet matches,",
            new PetMatchesFetcherResponseModel(stringPetIds)
        );
    }

}
