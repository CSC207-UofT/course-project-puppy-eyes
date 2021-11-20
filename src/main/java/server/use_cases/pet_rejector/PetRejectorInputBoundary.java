package server.use_cases.pet_rejector;

import server.use_cases.ResponseModel;

public interface PetRejectorInputBoundary {

    public ResponseModel rejectPets(PetRejectorRequestModel request);

}