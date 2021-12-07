package server.use_cases.pet_use_cases.pet_interactor.interactions;

import server.use_cases.ResponseModel;
import server.use_cases.repo_abstracts.IPetRepository;

/**
 * A particular implementation of the PetInteraction strategy to unmatch two pets.
 */
public class PetUnmatcher implements PetInteraction {

    private final IPetRepository petRepository;

    public PetUnmatcher(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Remove both pets from each other's list of matched pets.
     * @param pet1Id
     * @param pet2Id
     * @return a ResponseModel
     */
    @Override
    public ResponseModel interact(int pet1Id, int pet2Id) {
        if (this.petRepository.fetchMatches(pet1Id).contains(pet2Id) && this.petRepository.fetchMatches(pet2Id).contains(pet1Id)) {
            this.petRepository.unmatchPets(pet1Id, pet2Id);
            return new ResponseModel(true, "Successfully unmatched pet1 and pet2.");
        }

        return new ResponseModel(false, "These pets are not matched.");
    }

}
