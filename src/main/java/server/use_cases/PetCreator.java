package server.use_cases;

import server.entities.Pet;
import server.entities.User;
import server.use_cases.repo_abstracts.*;

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
    public ResponseModel createPet(PetCreatorRequestModel request) {
        int userId;
        try {
            userId = Integer.parseInt(request.getUserId());
        } catch (NumberFormatException e) {
            // Invalid user id
            return new ResponseModel(false, "ID must be an integer.");
        }

        User user;

        // Check if user exists
        try {
            user = userRepository.fetchUser(userId);
        } catch (UserNotFoundException exception) {
            return new ResponseModel(false, "User with ID: " + userId + " does not exist.");
        }

        if (!request.isRequestAuthorized()) {
            return new ResponseModel(false, "You are not authorized to make this request.");
        }

        Pet newPet = new Pet(userId, request.getName(), request.getAge(), request.getBreed(), request.getBiography()) {};

        // Check for valid inputs
        if (!newPet.isNameValid()) {
            return new ResponseModel(false, "Please enter a name of at least 3 characters.");
        }

        if (!newPet.isAgeValid()) {
            return new ResponseModel(false, "Please enter a non-negative age.");
        }

        if (!newPet.isBreedValid()) {
            return new ResponseModel(false, "Please enter a breed of at least 3 characters.");
        }

        int id = petRepository.createPet(userId, newPet.getName(), newPet.getAge(), newPet.getBreed(), newPet.getBiography());
        newPet.setId(id);

        return new ResponseModel(true, "Pet created successfully.",
                new PetCreatorResponseModel(
                        String.valueOf(newPet.getId()),
                        String.valueOf(userId),
                        newPet.getName(),
                        String.valueOf(newPet.getAge()),
                        newPet.getBreed(),
                        newPet.getBiography()
                )
        );
    }
}
