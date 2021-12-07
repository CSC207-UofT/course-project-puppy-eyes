package server.use_cases.pet_use_cases.pet_interactor.interactions;

import server.use_cases.ResponseModel;
import server.use_cases.repo_abstracts.IPetRepository;

/**
 * A particular implementation of the PetInteraction strategy to reject two pets.
 */
public class PetRejector implements PetInteraction {

    private final IPetRepository petRepository;

    public PetRejector(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Add pet with pet2Id to pet with pet1Id's list of rejected pets.
     * @param pet1Id
     * @param pet2Id
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
