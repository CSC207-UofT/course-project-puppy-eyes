package server.use_cases.pet_rejector;

import server.use_cases.repo_abstracts.ResponseModel;

public interface PetRejectorInputBoundary {

    public ResponseModel rejectPets(PetRejectorRequestModel request);

}