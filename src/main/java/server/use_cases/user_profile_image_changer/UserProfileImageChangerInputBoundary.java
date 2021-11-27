package server.use_cases.user_profile_image_changer;

import server.use_cases.ResponseModel;

public interface UserProfileImageChangerInputBoundary {

    public ResponseModel changeProfileImage(UserProfileImageChangerRequestModel request);

}