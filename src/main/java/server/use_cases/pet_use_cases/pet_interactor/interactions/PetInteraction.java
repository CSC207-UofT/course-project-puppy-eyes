package server.use_cases.pet_use_cases.pet_interactor.interactions;

import server.use_cases.ResponseModel;

/**
 * The PetInteraction interface is an application of the strategy design pattern.
 */
public interface PetInteraction {

    /**
     * Perform an interaction on pet1 and pet2 and return a ResponseModel
     *
     * @param pet1Id the id of pet1
     * @param pet2Id the id of pet2
     * @return a ResponseModel object
     */
    ResponseModel interact(int pet1Id, int pet2Id);

}
