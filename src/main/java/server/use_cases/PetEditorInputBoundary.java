package server.use_cases;

/**
 * An input boundary for the "edit pet" use case.
 */
public interface PetEditorInputBoundary {
    PetEditorResponseModel editPet(PetEditorRequestModel request);
}
