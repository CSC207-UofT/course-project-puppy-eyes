package server.use_cases.pet_editor;

import server.use_cases.repo_abstracts.ResponseModel;

/**
 * An input boundary for the "edit pet" use case.
 */
public interface PetEditorInputBoundary {
    ResponseModel editPet(PetEditorRequestModel request);
}
