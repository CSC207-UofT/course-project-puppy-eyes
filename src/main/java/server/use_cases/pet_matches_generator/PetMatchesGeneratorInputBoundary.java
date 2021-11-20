package server.use_cases.pet_matches_generator;

import server.use_cases.ResponseModel;

public interface PetMatchesGeneratorInputBoundary {

    public ResponseModel generatePotentialMatches(PetMatchesGeneratorRequestModel request);

}
