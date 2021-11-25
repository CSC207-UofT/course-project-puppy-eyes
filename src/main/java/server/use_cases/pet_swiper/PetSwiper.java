package server.use_cases.pet_swiper;

import server.entities.Pet;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.ResponseModel;

/**
 * A use case responsible for handling a pet "swiping" on another pet
 */
public class PetSwiper implements PetSwiperInputBoundary {

    private final IPetRepository petRepository;

    public PetSwiper(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public ResponseModel swipe(PetSwiperRequestModel request) {
        int pet1Id, pet2Id;

        try {
            pet1Id = Integer.parseInt(request.getFirstPetId());
            pet2Id = Integer.parseInt(request.getSecondPetId());
        } catch (NumberFormatException e) {
            // Invalid pet id
            return new ResponseModel(false, "ID must be an integer.");
        }

        try {
            Pet pet1 = petRepository.fetchPet(pet1Id);

            // Check if pet2 exists too
            petRepository.fetchPet(pet2Id);

            request.setUserId(String.valueOf(pet1.getUserId()));

            if (!request.isRequestAuthorized()) {
                return new ResponseModel(false, "You are not authorized to make this request.");
            }

            // If the first pet already swiped on the second pet, do nothing.
            if (petRepository.fetchSwipedOn(pet1Id).contains(pet2Id)) {
                return new ResponseModel(false, "The first pet is already matched with the second pet.");
            }

            // If the second pet already swiped on the first pet,
            // remove the first pet from the second pet's swiped list and match both of them
            if (petRepository.fetchSwipedOn(pet2Id).contains(pet1.getId())) {
                this.petRepository.unswipePets(pet1Id, pet2Id);
                this.petRepository.unswipePets(pet2Id, pet1Id);
                this.petRepository.matchPets(pet1Id, pet2Id);
                return new ResponseModel(true, "Successfully swiped and matched both pets.");
            }

            // If neither pet swiped on each other,
            // add the second pet to the first pet's swiped list
            this.petRepository.swipePets(pet1Id, pet2Id);
            return new ResponseModel(true, "Successfully swiped pet2 from pet1.");
        } catch (PetNotFoundException e) {
            // Pet not found
            return new ResponseModel(false, "Pet with ID: " + request.getFirstPetId() + " does not exist.");
        }
    }
}