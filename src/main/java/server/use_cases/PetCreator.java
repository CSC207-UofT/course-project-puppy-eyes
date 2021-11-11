package server.use_cases;

import server.entities.Pet;
import server.use_cases.repo_abstracts.IPetRepository;

/**
 * A use case responsible for creating new Pet.
 */
public class PetCreator implements PetCreatorInputBoundary {

    IPetRepository petRepository;

    public PetCreator(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Create a new PetCreatorResponseModel with given request.
     *
     * @param request Object containing registration data of the new pet.
     * @return a PetCreatorResponseModel that contains the created pet's basic information.
     */
    public PetCreatorResponseModel createPet(PetCreatorRequestModel request) {
        Pet newPet = new Pet(request.getName(), request.getAge(), request.getBreed(), request.getBiography()) {};

        int id = petRepository.createPet(newPet.getName(), newPet.getAge(), newPet.getBreed(), newPet.getBiography());
        newPet.setId(id);

        // TODO: Introduce cases where isSuccess is false
        return new PetCreatorResponseModel(true,
                newPet.getName(),
                newPet.getAge(),
                newPet.getBreed(),
                newPet.getBiography(),
                ((Integer) newPet.getId()).toString()
        );
    }
}
