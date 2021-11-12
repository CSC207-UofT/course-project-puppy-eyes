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

    /**
     * Create a new PetCreatorResponseModel with given request.
     *
     * @param request Object containing registration data of the new pet.
     * @return a PetCreatorResponseModel that contains the created pet's basic information.
     */
    public PetCreatorResponseModel createPet(PetCreatorRequestModel request) {
        Pet newPet = new Pet(request.getUserId(), request.getName(), request.getAge(), request.getBreed(), request.getBiography()) {};

        // TODO check if user exists from userId in request

        int id = petRepository.createPet(request.getUserId(), newPet.getName(), newPet.getAge(), newPet.getBreed(), newPet.getBiography());
        newPet.setId(id);

        // TODO: Introduce cases where isSuccess is false
        return new PetCreatorResponseModel(
                true,
                String.valueOf(newPet.getId()),
                String.valueOf(newPet.getUserId()),
                newPet.getName(),
                String.valueOf(newPet.getAge()),
                newPet.getBreed(),
                newPet.getBiography()
        );
    }
}
