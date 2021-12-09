package server.use_cases.pet_use_cases.pet_interactor.interactions;

import server.use_cases.ResponseModel;
import server.use_cases.repo_abstracts.IPetRepository;

/**
 * A particular implementation of the PetInteraction strategy to unswipe two pets.
 */
public class PetUnswiper implements PetInteraction {

    private final IPetRepository petRepository;

    public PetUnswiper(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Remove pet with pet2Id from pet with pet1Id's list of swiped pets.
     *
     * @param pet1Id the id of pet1
     * @param pet2Id the id of pet2
     * @return a ResponseModel
     */
    @Override
    public ResponseModel interact(int pet1Id, int pet2Id) {
        // If the first pet already swiped on the second pet, remove the second pet from the first pet's swiped list
        if (this.petRepository.fetchSwipedOn(pet1Id).contains(pet2Id)) {
            this.petRepository.unswipePets(pet1Id, pet2Id);
            return new ResponseModel(true, "Successfully removed pet2 from pet1's swiped list.");
        }

        return new ResponseModel(false, "Pet2 is not in pet1's swiped list.");
    }

}
