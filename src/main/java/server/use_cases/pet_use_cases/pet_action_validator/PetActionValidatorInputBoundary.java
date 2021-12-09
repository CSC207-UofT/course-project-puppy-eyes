package server.use_cases.pet_use_cases.pet_action_validator;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "pet action validator" use case.
 */
public interface PetActionValidatorInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request the request object
     * @return a ResponseModel object
     */
    ResponseModel validateAction(PetActionValidatorRequestModel request);

}
