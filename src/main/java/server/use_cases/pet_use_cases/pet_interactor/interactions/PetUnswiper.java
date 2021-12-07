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
     * @param pet1Id
     * @param pet2Id
     * @return a ResponseModel
     */
    @Override
    public ResponseModel interact(int pet1Id, int pet2Id) {
        if (this.petRepository.fetchMatches(pet1Id).contains(pet2Id) || this.petRepository.fetchMatches(pet2Id).contains(pet1Id)) {
            return new ResponseModel(false, "Both pets are already matched with each other.");
        }

        // Remove both pets from each other's swiped list
        this.petRepository.unswipePets(pet1Id, pet2Id);
        this.petRepository.unswipePets(pet2Id, pet1Id);

        // Add the second pet to the first pet's rejected list
        this.petRepository.rejectPets(pet1Id, pet2Id);
        return new ResponseModel(true, "Successfully rejected pet2 from pet1.");
    }

}
