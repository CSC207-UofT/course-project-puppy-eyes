package server.use_cases.pet_use_cases.pet_creator;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "create pet" use case.
 */
public interface PetCreatorInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request the request object
     * @return a ResponseModel object
     */
    ResponseModel createPet(PetCreatorRequestModel request);
}
