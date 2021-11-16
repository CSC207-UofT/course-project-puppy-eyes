package server.use_cases;

import server.entities.Pet;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.ResponseModel;

import java.util.List;
import java.util.stream.Collectors;

public class PetMatchesFetcher implements PetMatchesFetcherInputBoundary {
    private final IPetRepository petRepository;

    public PetMatchesFetcher(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Create a new ResponseModel with ResponseData of PetMatchesFetcherResponse for the given request.
     *
     * @param request Object containing id of the pet.
     * @return a PetMatchesFetcherResponseModel that a list of pet ids that the pet has matched with.
     */
    @Override
    public ResponseModel fetchPetMatches(PetMatchesFetcherRequestModel request)  {
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

            List<String> stringPetIds = petRepository.fetchMatches(id).stream().
                    map(String::valueOf).
                    collect(Collectors.toList());

            return new ResponseModel(
                true,
                "Successfully retrieved pet matches,",
                new PetMatchesFetcherResponseModel(stringPetIds)
            );
        } catch (PetNotFoundException exception) {
            return new ResponseModel(false, "Pet with ID: " + request.getPetId() + " does not exist.");
        }
    }

}
