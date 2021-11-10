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

    public PetCreatorResponseModel createPet(PetCreatorRequestModel request) {
        Pet newPet = new Pet(request.getName(), request.getAge()) {};

        int id = petRepository.createPet(newPet.getName(), newPet.getAge());
        newPet.setId(id);

        // TODO: Introduce cases where isSuccess is false
        return new PetCreatorResponseModel(true,
                newPet.getName(),
                newPet.getAge(),
                ((Integer) newPet.getId()).toString()
        );
    }
}
