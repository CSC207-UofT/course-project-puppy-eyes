package server.use_cases.pet_swipes_fetcher;

import server.entities.Pet;
import server.use_cases.pet_matches_fetcher.PetMatchesFetcherResponseModel;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.ResponseModel;

import java.util.List;
import java.util.stream.Collectors;

public class PetSwipesFetcher implements PetSwipesFetcherInputBoundary {
    private final IPetRepository petRepository;

    public PetSwipesFetcher(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Create a new PetSwipesFetcher with given request.
     *
     * @param request Object containing id of the pet.
     * @return a PetSwipesFetcherResponseModel that a list of pet ids that the pet has swiped on.
     */
    @Override
    public ResponseModel fetchPetSwipes(PetSwipesFetcherRequestModel request)  {
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

            List<String> stringPetIds = petRepository.fetchSwipedOn(id).stream().
                    map(String::valueOf).
                    collect(Collectors.toList());

            return new ResponseModel(
                    true,
                    "Successfully retrieved pet swipes.",
                    new PetMatchesFetcherResponseModel(stringPetIds)
            );
        } catch (PetNotFoundException exception) {
            return new ResponseModel(false, "Pet with ID: " + request.getPetId() + " does not exist.");
        }
    }

}
