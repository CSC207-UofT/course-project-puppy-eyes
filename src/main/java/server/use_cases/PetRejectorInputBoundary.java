package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

public interface PetRejectorInputBoundary {

    public ResponseModel rejectPets(PetRejectorRequestModel request);

}