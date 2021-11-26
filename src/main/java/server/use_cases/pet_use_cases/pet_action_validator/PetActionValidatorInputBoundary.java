package server.use_cases.pet_use_cases.pet_action_validator;

import server.use_cases.ResponseModel;

public interface PetActionValidatorInputBoundary {

    public ResponseModel validateAction(PetActionValidatorRequestModel request);

}
