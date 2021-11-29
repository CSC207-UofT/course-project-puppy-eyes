package server.use_cases.pet_use_cases.pet_editor;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "edit pet" use case.
 */
public interface PetEditorInputBoundary {
    ResponseModel editPet(PetEditorRequestModel request);
}
