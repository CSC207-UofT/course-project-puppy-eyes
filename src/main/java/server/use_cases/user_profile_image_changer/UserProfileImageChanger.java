package server.use_cases.user_profile_image_changer;

import server.drivers.IImageService;
import server.drivers.ImageUploadResponse;
import server.use_cases.ResponseModel;
import server.use_cases.repo_abstracts.IImageRepository;

import java.util.Map;

public class UserProfileImageChanger implements UserProfileImageChangerInputBoundary {

    private final IImageService imageService;
    private final IImageRepository imageRepository;

    public UserProfileImageChanger(IImageRepository imageRepository, IImageService imageService) {
        this.imageRepository = imageRepository;
        this.imageService = imageService;
    }

    @Override
    public ResponseModel changeProfileImage(UserProfileImageChangerRequestModel request) {
        // since the userId is coming from the request header, we can safely assume it is a correct integer
        int userId = Integer.parseInt(request.getHeaderUserId());

        // Fetch the response from uploading the base64 image
        ImageUploadResponse uploadResponse = imageService.uploadBase64(request.getBase64Encoded());

        if (uploadResponse == null) {
            return new ResponseModel(false, "An error occurred while trying to upload this image.");
        }

        String url = uploadResponse.getUrl();
        String assetId = uploadResponse.getAssetId();

        imageRepository.setUserProfileImage(userId, assetId, url);

        return new ResponseModel(
                true,
                "Successfuly changed the user's profile image.",
                new UserProfileImageChangerResponseModel(assetId, url)
        );
    }

}
