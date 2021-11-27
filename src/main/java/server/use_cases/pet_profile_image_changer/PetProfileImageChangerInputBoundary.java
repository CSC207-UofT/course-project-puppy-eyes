package server.use_cases.pet_profile_image_changer;

import server.use_cases.ResponseModel;

public interface PetProfileImageChangerInputBoundary {

    public ResponseModel changeProfileImage(PetProfileImageChangerRequestModel request);

}