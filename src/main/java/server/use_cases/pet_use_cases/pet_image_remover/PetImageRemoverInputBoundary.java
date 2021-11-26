package server.use_cases.pet_use_cases.pet_image_remover;

import server.use_cases.ResponseModel;

public interface PetImageRemoverInputBoundary {

    public ResponseModel removeImage(PetImageRemoverRequestModel request);

}
