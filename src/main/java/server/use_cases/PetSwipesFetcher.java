package server.use_cases;

import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.PetRepositoryPetProfileFetcherResponse;

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
    public PetSwipesFetcherResponseModel fetchPetSwipes(PetSwipesFetcherRequestModel request)  {
        int id;
        try {
            id = Integer.parseInt(request.getPetId());
        } catch (NumberFormatException e) {
            // Invalid pet id
            return new PetSwipesFetcherResponseModel(false, null);
        }

        try {
            List<Integer> petIds = petRepository.fetchPetSwipes(id);
            List<String> stringPetIds = petIds.stream().map(String::valueOf).collect(Collectors.toList());

            return new PetSwipesFetcherResponseModel(true, stringPetIds);
        } catch (PetNotFoundException exception) {
            return new PetSwipesFetcherResponseModel(false, null);
        }
    }

}
