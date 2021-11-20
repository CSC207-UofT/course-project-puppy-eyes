package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

public interface PetProfileValidatorInputBoundary {

    public ResponseModel validateProfile(PetProfileValidatorRequestModel request);

}