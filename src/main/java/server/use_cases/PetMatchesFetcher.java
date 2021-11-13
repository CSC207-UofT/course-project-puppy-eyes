package server.use_cases;

import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class PetMatchesFetcher implements PetMatchesFetcherInputBoundary {
    private final IPetRepository petRepository;

    public PetMatchesFetcher(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Create a new PetMatchesFetcher with given request.
     *
     * @param request Object containing id of the pet.
     * @return a PetMatchesFetcherResponseModel that a list of pet ids that the pet has matched with.
     */
    @Override
    public PetMatchesFetcherResponseModel fetchPetMatches(PetMatchesFetcherRequestModel request)  {
        int id;
        try {
            id = Integer.parseInt(request.getPetId());
        } catch (NumberFormatException e) {
            // Invalid pet id
            return new PetMatchesFetcherResponseModel(false, null);
        }

        try {
            List<Integer> petIds = petRepository.fetchPetMatches(id);
            List<String> stringPetIds = petIds.stream().map(String::valueOf).collect(Collectors.toList());

            return new PetMatchesFetcherResponseModel(true, stringPetIds);
        } catch (PetNotFoundException exception) {
            return new PetMatchesFetcherResponseModel(false, null);
        }
    }

}
