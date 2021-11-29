package server.use_cases.profile_image_fetcher;

import server.use_cases.ResponseModel;

public interface ProfileImageFetcherInputBoundary {

    public ResponseModel fetchProfileImage(ProfileImageFetcherRequestModel request);

}
