package server.use_cases.pet_use_cases.pet_profile_fetcher;

import server.entities.Pet;
import server.use_cases.repo_abstracts.IImageRepository;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.ResponseModel;

public class PetProfileFetcher implements PetProfileFetcherInputBoundary {
    private final IPetRepository petRepository;
    private final IImageRepository imageRepository;

    public PetProfileFetcher(IPetRepository petRepository, IImageRepository imageRepository) {
        this.petRepository = petRepository;
        this.imageRepository = imageRepository;
    }

    /**
     * Create a new PetProfileFetcherResponseModel with given request.
     *
     * @param request Object containing id of the pet.
     * @return a PetProfileFetcherResponseModel that contains the created pet's basic information.
     */
    @Override
    public ResponseModel fetchPetProfile(PetProfileFetcherRequestModel request) {
        int petId = Integer.parseInt(request.getPetId());
        Pet pet = petRepository.fetchPet(petId);

        if (pet == null) {
            return new ResponseModel(false, "Pet with ID: " + petId + " does not exist.");
        }

        String profileImgUrl = imageRepository.fetchPetProfileImageLink(petId);

        return new ResponseModel(
            true,
            "Successfully fetched pet profile.",
            new PetProfileFetcherResponseModel(
                pet.getUserId(),
                pet.getName(),
                pet.getAge(),
                pet.getBreed(),
                pet.getBiography(),
                profileImgUrl == null ? "" : profileImgUrl
            )
        );
    }

}
