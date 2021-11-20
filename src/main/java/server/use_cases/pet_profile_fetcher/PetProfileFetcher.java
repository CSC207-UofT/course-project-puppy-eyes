package server.use_cases.pet_profile_fetcher;

import server.entities.Pet;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.ResponseModel;

public class PetProfileFetcher implements PetProfileFetcherInputBoundary {
    private final IPetRepository petRepository;

    public PetProfileFetcher(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Create a new PetProfileFetcherResponseModel with given request.
     *
     * @param request Object containing id of the pet.
     * @return a PetProfileFetcherResponseModel that contains the created pet's basic information.
     */
    @Override
    public ResponseModel fetchPetProfile(PetProfileFetcherRequestModel request) {
        int id;
        try {
            id = Integer.parseInt(request.getPetId());
        } catch (NumberFormatException e) {
            // Invalid pet id
            return new ResponseModel(false, "ID must be an integer.");
        }

        try {
            Pet pet = petRepository.fetchPet(id);
            request.setUserId(String.valueOf(pet.getUserId()));

            if (!request.isRequestAuthorized()) {
                return new ResponseModel(false, "You are not authorized to make this request.");
            }

            return new ResponseModel(
                true,
                "Successfully fetched pet profile.",
                new PetProfileFetcherResponseModel(
                    pet.getName(),
                    pet.getAge(),
                    pet.getBreed(),
                    pet.getBiography()
                )
            );
        } catch (PetNotFoundException e) {
            // Pet not found
            return new ResponseModel(false, "Pet with ID: " + request.getPetId() + " does not exist.");
        }
    }

}
