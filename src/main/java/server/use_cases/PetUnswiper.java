package server.use_cases;

import server.entities.Pet;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.ResponseModel;

/**
 * A use case responsible for handling a pet "unswiping" on another pet
 */
public class PetUnswiper implements PetUnswiperInputBoundary {

    private final IPetRepository petRepository;

    public PetUnswiper(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public ResponseModel unswipePets(PetUnswiperRequestModel request) {
        int pet1Id, pet2Id;

        try {
            pet1Id = request.getFirstPetId();
            pet2Id = request.getSecondPetId();
        } catch (NumberFormatException e) {
            // Invalid pet id
            return new ResponseModel(false, "ID must be an integer.");
        }

        try {
            Pet pet1 = petRepository.fetchPet(pet1Id);

            // Check if pet2 exists
            petRepository.fetchPet(pet2Id);

            request.setUserId(String.valueOf(pet1.getUserId()));

            if (!request.isRequestAuthorized()) {
                return new ResponseModel(false, "You are not authorized to make this request.");
            }

            // If the first pet already swiped on the second pet, remove the second pet from the first pet's swiped list
            if (this.petRepository.fetchSwipedOn(pet1Id).contains(pet2Id)) {
                this.petRepository.unswipePets(pet1Id, pet2Id);
                return new ResponseModel(true, "Successfully removed pet2 from pet1's swiped list.");
            }

            return new ResponseModel(false, "Pet2 is not in pet1's swiped list.");
        } catch (PetNotFoundException e) {
            // Pet not found
            return new ResponseModel(false, "Pet with ID: " + request.getFirstPetId() + " does not exist.");
        }
    }

}