package server.use_cases;

import server.entities.Pet;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.IRelationRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.ResponseModel;

/**
 * A use case responsible for handling a pet "swiping" on another pet
 */
public class PetSwiper implements PetSwiperInputBoundary {

    private final IPetRepository petRepository;
    private final IRelationRepository relationRepository;

    public PetSwiper(IRelationRepository relationRepository, IPetRepository petRepository) {
        this.petRepository = petRepository;
        this.relationRepository = relationRepository;
    }

    @Override
    public ResponseModel swipe(PetSwiperRequestModel request) {
        int pet1Id, pet2Id;

        try {
            pet1Id = request.getFirstPetId();
            pet2Id = request.getSecondPetId();
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

            final String swipeRelationType = "SWIPE";
            final String matchRelationType = "MATCH";

            // If the first pet already swiped on the second pet, do nothing.
            if (this.relationRepository.hasRelation(request.getFirstPetId(), request.getSecondPetId(), swipeRelationType)) {
                return new ResponseModel(false, "The first pet is already matched with the second pet.");
            }

            // If the second pet already swiped on the first pet,
            // remove the first pet from the second pet's swiped list and match both of them
            if (this.relationRepository.hasRelation(request.getSecondPetId(), request.getFirstPetId(), swipeRelationType)) {
                this.relationRepository.removeRelation(request.getSecondPetId(), request.getFirstPetId(), swipeRelationType);
                this.relationRepository.addRelation(request.getFirstPetId(), request.getSecondPetId(), matchRelationType);
                this.relationRepository.addRelation(request.getSecondPetId(), request.getFirstPetId(), matchRelationType);
                return new ResponseModel(true, "Successfully swiped and matched both pets.");
            }

            // If neither pet swiped on each other,
            // add the second pet to the first pet's swiped list
            this.relationRepository.addRelation(request.getFirstPetId(), request.getSecondPetId(), swipeRelationType);
            return new ResponseModel(true, "Successfully swiped pet2 from pet1.");
        } catch (PetNotFoundException e) {
            // Pet not found
            return new ResponseModel(false, "Pet with ID: " + request.getFirstPetId() + " does not exist.");
        }
    }
}