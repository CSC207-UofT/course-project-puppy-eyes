package server.use_cases;

/**
 * An input boundary for the "create pet" use case.
 */
public interface PetCreatorInputBoundary {
    PetCreatorResponseModel createPet(PetCreatorRequestModel request);
}
