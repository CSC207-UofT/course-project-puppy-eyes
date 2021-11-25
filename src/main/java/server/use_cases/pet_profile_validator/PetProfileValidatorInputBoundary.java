package server.use_cases.pet_profile_validator;

import server.use_cases.ResponseModel;

public interface PetProfileValidatorInputBoundary {

    public ResponseModel validateProfile(PetProfileValidatorRequestModel request);

}