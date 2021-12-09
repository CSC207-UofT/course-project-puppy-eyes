package server.use_cases.pet_use_cases.pet_editor;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "edit pet" use case.
 */
public interface PetEditorInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request the request object
     * @return a ResponseModel object
     */
    ResponseModel editPet(PetEditorRequestModel request);
}
