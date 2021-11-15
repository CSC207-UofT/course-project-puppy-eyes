package server.use_cases;

import server.entities.Pet;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.IRelationRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.ResponseModel;

/**
 * A use case responsible for handling a pet "rejecting" another pet.
 */
public class PetRejector implements PetRejectorInputBoundary {

    private final IPetRepository petRepository;
    private final IRelationRepository relationRepository;

    public PetRejector(IRelationRepository relationRepository, IPetRepository petRepository) {
        this.petRepository = petRepository;
        this.relationRepository = relationRepository;
    }

    @Override
    public ResponseModel rejectPets(PetRejectorRequestModel request) {
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

            final String rejectRelationType = "REJECT";
            final String swipeRelationType = "SWIPE";
            final String matchRelationType = "MATCH";

            // Do nothing if the pets are already matched
            if (this.relationRepository.hasRelation(pet.getId(), pet2.getId(), matchRelationType)
                    || this.relationRepository.hasRelation(pet2.getId(), pet.getId(), matchRelationType)) {
                new ResponseModel(false, "Both pets are already matched with each other.");
            }

            // Remove both pets from each other's swiped list
            this.relationRepository.removeRelation(pet.getId(), pet2.getId(), swipeRelationType);
            this.relationRepository.removeRelation(pet2.getId(), pet.getId(), swipeRelationType);

            // Add the second pet to the first pet's rejected list
            this.relationRepository.addRelation(request.getFirstPetId(), request.getSecondPetId(), rejectRelationType);

            new ResponseModel(false, "Successfully rejected pet2 from pet1.");
        } catch (PetNotFoundException e) {
            // Pet not found
            return new ResponseModel(false, "Pet with ID: " + request.getFirstPetId() + " does not exist.");
        }

        return new ResponseModel(false, "An unexpected error occurred.");
    }

}