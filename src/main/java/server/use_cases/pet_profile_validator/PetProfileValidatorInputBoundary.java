package server.use_cases.pet_profile_validator;

import server.use_cases.repo_abstracts.ResponseModel;

public interface PetProfileValidatorInputBoundary {

    public ResponseModel validateProfile(PetProfileValidatorRequestModel request);

}