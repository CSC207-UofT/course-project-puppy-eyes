package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

import java.io.IOException;

public interface PetMatchesGeneratorInputBoundary {

    public ResponseModel generatePotentialMatches(PetMatchesGeneratorRequestModel request);

}
