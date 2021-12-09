package server.use_cases.pet_use_cases.pet_matches_generator;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "pet matches generator" use case
 */
public interface PetMatchesGeneratorInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request the request object
     * @return a ResponseModel object
     */
    ResponseModel generatePotentialMatches(PetMatchesGeneratorRequestModel request);

}
