package server.use_cases.pet_use_cases.pet_profile_fetcher;

import server.entities.Pet;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.ResponseModel;

public class PetProfileFetcher implements PetProfileFetcherInputBoundary {
    private final IPetRepository petRepository;
    private final PetActionValidatorInputBoundary petActionValidator;

    public PetProfileFetcher(IPetRepository petRepository, PetActionValidatorInputBoundary petActionValidator) {
        this.petRepository = petRepository;
        this.petActionValidator = petActionValidator;
    }

    /**
     * Create a new PetProfileFetcherResponseModel with given request.
     *
     * @param request Object containing id of the pet.
     * @return a PetProfileFetcherResponseModel that contains the created pet's basic information.
     */
    @Override
    public ResponseModel fetchPetProfile(PetProfileFetcherRequestModel request) {
        ResponseModel validateActionResponse = petActionValidator.validateAction(new PetActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getPetId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int petId = Integer.parseInt(request.getPetId());
        Pet pet = petRepository.fetchPet(petId);

        return new ResponseModel(
            true,
            "Successfully fetched pet profile.",
            new PetProfileFetcherResponseModel(
                pet.getUserId(),
                pet.getName(),
                pet.getAge(),
                pet.getBreed(),
                pet.getBiography()
            )
        );
    }

}
