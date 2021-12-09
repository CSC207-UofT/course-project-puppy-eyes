package server.use_cases.pet_use_cases.pet_interactor.interactions;

import server.use_cases.ResponseModel;
import server.use_cases.repo_abstracts.IPetRepository;

/**
 * A particular implementation of the PetInteraction strategy to swipe two pets.
 */
public class PetSwiper implements PetInteraction {

    private final IPetRepository petRepository;

    public PetSwiper(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Add pet with pet2Id to pet with pet1Id's list of swiped pets.
     *
     * @param pet1Id the id of pet1
     * @param pet2Id the id of pet2
     * @return a ResponseModel
     */
    @Override
    public ResponseModel interact(int pet1Id, int pet2Id) {
        // If the first pet already swiped on the second pet, do nothing.
        if (petRepository.fetchSwipedOn(pet1Id).contains(pet2Id)) {
            return new ResponseModel(false, "The first pet is already matched with the second pet.");
        }

        // If the second pet already swiped on the first pet,
        // remove the first pet from the second pet's swiped list and match both of them
        if (petRepository.fetchSwipedOn(pet2Id).contains(pet1Id)) {
            this.petRepository.unswipePets(pet1Id, pet2Id);
            this.petRepository.unswipePets(pet2Id, pet1Id);
            this.petRepository.matchPets(pet1Id, pet2Id);
            return new ResponseModel(true, "Successfully swiped and matched both pets.");
        }

        // If neither pet swiped on each other,
        // add the second pet to the first pet's swiped list
        this.petRepository.swipePets(pet1Id, pet2Id);
        return new ResponseModel(true, "Successfully swiped pet2 from pet1.");
    }

}
