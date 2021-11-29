package server.use_cases.profile_image_fetcher;

import server.use_cases.ResponseModel;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.repo_abstracts.IImageRepository;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidatorInputBoundary;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidatorRequestModel;

public class ProfileImageFetcher implements ProfileImageFetcherInputBoundary {

    private final IImageRepository imageRepository;
    private final PetActionValidatorInputBoundary petActionValidator;
    private final UserActionValidatorInputBoundary userActionValidator;

    public ProfileImageFetcher(IImageRepository imageRepository, PetActionValidatorInputBoundary petActionValidator,
                               UserActionValidatorInputBoundary userActionValidator) {
        this.imageRepository = imageRepository;
        this.petActionValidator = petActionValidator;
        this.userActionValidator = userActionValidator;
    }

    @Override
    public ResponseModel fetchProfileImage(ProfileImageFetcherRequestModel request) {
        if (request.isUser()) {
            return fetchUserProfileImage(request);
        } else {
            return fetchPetProfileImage(request);
        }
    }

    private ResponseModel fetchUserProfileImage(ProfileImageFetcherRequestModel request) {
        ResponseModel validateActionResponse = userActionValidator.validateAction(new UserActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getOwnerId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int userId = Integer.parseInt(request.getUserId());

        String url = imageRepository.fetchUserProfileImageLink(userId);

        if (url == null) {
            return new ResponseModel(false, "Could not find the image.");
        }

        return new ResponseModel(true, "Successfully fetched user profile image.",
                new ProfileImageFetcherResponseModel(url));
    }

    private ResponseModel fetchPetProfileImage(ProfileImageFetcherRequestModel request) {
        ResponseModel validateActionResponse = petActionValidator.validateAction(new PetActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getOwnerId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int petId = Integer.parseInt(request.getOwnerId());

        String url = imageRepository.fetchPetProfileImageLink(petId);

        if (url == null) {
            return new ResponseModel(false, "Could not find the image.");
        }

        return new ResponseModel(true, "Successfully fetched pet profile image.",
                new ProfileImageFetcherResponseModel(url));
    }

}
