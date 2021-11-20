package server.use_cases.pet_creator;

import server.use_cases.repo_abstracts.ResponseModel;

/**
 * An input boundary for the "create pet" use case.
 */
public interface PetCreatorInputBoundary {
    ResponseModel createPet(PetCreatorRequestModel request);
}
