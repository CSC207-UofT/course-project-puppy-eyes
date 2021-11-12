package server.use_cases;

import server.entities.Pet;
import server.entities.User;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.IUserRepository;

/**
 * A use case responsible for creating new Pet.
 */
public class PetCreator implements PetCreatorInputBoundary {

    IPetRepository petRepository;
    IUserRepository userRepository;

    public PetCreator(IPetRepository petRepository, IUserRepository userRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    public PetCreatorResponseModel createPet(PetCreatorRequestModel request) {
        Pet newPet = new Pet(request.getUserId(), request.getName(), request.getAge()) {};

        // TODO check if user exists from userId in request

        int id = petRepository.createPet(request.getUserId(), newPet.getName(), newPet.getAge());
        newPet.setId(id);

        // TODO: Introduce cases where isSuccess is false
        return new PetCreatorResponseModel(true,
                String.valueOf(newPet.getId()),
                String.valueOf(newPet.getUserId()),
                newPet.getName(),
                String.valueOf(newPet.getAge())
        );
    }
}
