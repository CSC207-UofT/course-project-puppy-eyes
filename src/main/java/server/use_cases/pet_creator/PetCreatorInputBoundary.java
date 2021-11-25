package server.use_cases.pet_creator;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "create pet" use case.
 */
public interface PetCreatorInputBoundary {
    ResponseModel createPet(PetCreatorRequestModel request);
}
