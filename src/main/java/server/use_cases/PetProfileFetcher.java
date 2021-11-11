package server.use_cases;

import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.PetRepositoryPetProfileFetcherResponse;

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
    public PetProfileFetcherResponseModel fetchPetProfile(PetProfileFetcherRequestModel request) {
        int id;
        try {
            id = Integer.parseInt(request.getPetId());
        } catch (NumberFormatException e) {
            // Invalid pet id
            return new PetProfileFetcherResponseModel(false, "", -1, "", "");
        }

        PetRepositoryPetProfileFetcherResponse pet;
        try {
            pet = petRepository.fetchPetProfile(id);

            return new PetProfileFetcherResponseModel(true, pet.getName(), pet.getAge(), pet.getBreed(), pet.getBiography());
        } catch (PetNotFoundException e) {
            // Pet not found
            return new PetProfileFetcherResponseModel(false, "", -1, "", "");
        }
    }

}
