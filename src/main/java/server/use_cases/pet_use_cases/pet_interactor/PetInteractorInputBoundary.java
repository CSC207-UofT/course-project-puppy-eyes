package server.use_cases.pet_use_cases.pet_interactor;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "pet interactor" use case, which includes
 * pet swiping, unswiping, rejecting, unmatching, and matching.
 */
public interface PetInteractorInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request the request object
     * @return a ResponseModel object
     */
    ResponseModel interact(PetInteractorRequestModel request);

}