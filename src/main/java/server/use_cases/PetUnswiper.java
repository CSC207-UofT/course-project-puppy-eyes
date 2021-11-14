package server.use_cases;

import server.entities.Pet;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.IRelationRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.ResponseModel;

/**
 * A use case responsible for handling a pet "unswiping" on another pet
 */
public class PetUnswiper implements PetUnswiperInputBoundary {

    private final IPetRepository petRepository;
    private final IRelationRepository relationRepository;

    public PetUnswiper(IRelationRepository relationRepository, IPetRepository petRepository) {
        this.petRepository = petRepository;
        this.relationRepository = relationRepository;
    }

    @Override
    public ResponseModel unswipePets(PetUnswiperRequestModel request) {
        int pet1Id, pet2Id;

        try {
            pet1Id = request.getFirstPetId();
            pet2Id = request.getFirstPetId();
        } catch (NumberFormatException e) {
            // Invalid pet id
            return new ResponseModel(false, "ID must be an integer.");
        }

        try {
            Pet pet = petRepository.fetchPet(pet1Id);
            Pet pet2 = petRepository.fetchPet(pet2Id);
            request.setUserId(String.valueOf(pet.getUserId()));

            if (!request.isRequestAuthorized()) {
                return new ResponseModel(false, "You are not authorized to make this request.");
            }

            final String relationType = "SWIPE";

            // If the first pet already swiped on the second pet, remove the second pet from the first pet's swiped list
            if (this.relationRepository.hasRelation(pet.getId(), pet2.getId(), relationType)) {
                this.relationRepository.removeRelation(pet.getId(), pet2.getId(), relationType);
            }
        } catch (PetNotFoundException e) {
            // Pet not found
            return new ResponseModel(false, "Pet with ID: " + request.getFirstPetId() + " does not exist.");
        }

        return new ResponseModel(false, "An unexpected error occurred.");
    }

}