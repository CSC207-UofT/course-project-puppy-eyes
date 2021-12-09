package server.use_cases.pet_use_cases.pet_profile_image_changer;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "pet profile image changer" use case.
 */
public interface PetProfileImageChangerInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request the request object
     * @return a ResponseModel object
     */
    ResponseModel changeProfileImage(PetProfileImageChangerRequestModel request);

}