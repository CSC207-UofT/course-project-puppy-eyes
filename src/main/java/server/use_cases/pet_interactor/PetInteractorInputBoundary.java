package server.use_cases.pet_interactor;

import server.use_cases.ResponseModel;

public interface PetInteractorInputBoundary {

    public ResponseModel interact(PetInteractorRequestModel request);

}