package server.use_cases.pet_image_adder;

import server.use_cases.ResponseModel;

public interface PetImageAdderInputBoundary {

    public ResponseModel addImage(PetImageAdderRequestModel request);

}
