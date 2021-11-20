package server.use_cases;

import server.entities.Pet;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.ResponseModel;

/**
 * A use case responsible for handling a pet "rejecting" another pet.
 */
public class PetRejector implements PetRejectorInputBoundary {

    private final IPetRepository petRepository;

    public PetRejector(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public ResponseModel rejectPets(PetRejectorRequestModel request) {
        int pet1Id, pet2Id;

        try {
            pet1Id = Integer.parseInt(request.getFirstPetId());
            pet2Id = Integer.parseInt(request.getFirstPetId());
        } catch (NumberFormatException e) {
            // Invalid pet id
            return new ResponseModel(false, "ID must be an integer.");
        }

        try {
            Pet pet = petRepository.fetchPet(pet1Id);

            // Check if pet2 exists too
            petRepository.fetchPet(pet2Id);

            request.setUserId(String.valueOf(pet.getUserId()));

            if (!request.isRequestAuthorized()) {
                return new ResponseModel(false, "You are not authorized to make this request.");
            }

            // Do nothing if the pets are already matched
            if (this.petRepository.fetchMatches(pet1Id).contains(pet2Id)) {
                new ResponseModel(false, "Both pets are already matched with each other.");
            }

            // Remove both pets from each other's swiped list
            this.petRepository.unswipePets(pet1Id, pet2Id);
            this.petRepository.unswipePets(pet2Id, pet1Id);

            // Add the second pet to the first pet's rejected list
            this.petRepository.rejectPets(pet1Id, pet2Id);
            new ResponseModel(false, "Successfully rejected pet2 from pet1.");
        } catch (PetNotFoundException e) {
            // Pet not found
            return new ResponseModel(false, "Pet with ID: " + e.getId() + " does not exist.");
        }

        return new ResponseModel(false, "An unexpected error occurred.");
    }

}