package server.use_cases.pet_use_cases.pet_profile_validator;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "pet profile validator" use case
 */
public interface PetProfileValidatorInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     * @param request
     * @return a ResponseModel object
     */
    ResponseModel validateProfile(PetProfileValidatorRequestModel request);

}