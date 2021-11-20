package server.use_cases.pet_matches_generator;

import server.use_cases.repo_abstracts.ResponseModel;

public interface PetMatchesGeneratorInputBoundary {

    public ResponseModel generatePotentialMatches(PetMatchesGeneratorRequestModel request);

}
