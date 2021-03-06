package server.use_cases.user_use_cases.user_profile_image_changer;

import server.drivers.IImageService;
import server.drivers.ImageUploadResponse;
import server.use_cases.ResponseModel;
import server.use_cases.repo_abstracts.IImageRepository;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidatorInputBoundary;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidatorRequestModel;

public class UserProfileImageChanger implements UserProfileImageChangerInputBoundary {

    private final IImageService imageService;
    private final IImageRepository imageRepository;
    private final UserActionValidatorInputBoundary userActionValidator;

    public UserProfileImageChanger(IImageRepository imageRepository, IImageService imageService,
                                   UserActionValidatorInputBoundary userActionValidator) {
        this.imageRepository = imageRepository;
        this.imageService = imageService;
        this.userActionValidator = userActionValidator;
    }

    @Override
    public ResponseModel changeProfileImage(UserProfileImageChangerRequestModel request) {
        ResponseModel validateActionResponse = userActionValidator.validateAction(new UserActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getHeaderUserId() // userId == headerUserId
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int userId = Integer.parseInt(request.getHeaderUserId());

        // Fetch the response from uploading the base64 image
        ImageUploadResponse uploadResponse = imageService.uploadBase64(request.getBase64Encoded());

        if (uploadResponse == null) {
            return new ResponseModel(false, "An error occurred while trying to upload this image.");
        }

        String url = uploadResponse.getUrl();
        String assetId = uploadResponse.getAssetId();

        boolean isSuccess = imageRepository.setUserProfileImage(userId, assetId, url);

        return new ResponseModel(
                isSuccess,
                "Successfully changed the user's profile image.",
                new UserProfileImageChangerResponseModel(assetId, url)
        );
    }

}
