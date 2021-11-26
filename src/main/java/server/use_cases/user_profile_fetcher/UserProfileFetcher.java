package server.use_cases.user_profile_fetcher;

import server.entities.User;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.ResponseModel;
import server.use_cases.user_action_validator.UserActionValidatorInputBoundary;
import server.use_cases.user_action_validator.UserActionValidatorRequestModel;

import java.util.List;

/**
 * A use case responsible for fetching a user's profile based on a user id.
 */
public class UserProfileFetcher implements UserProfileFetcherInputBoundary {

    private final IUserRepository userRepository;
    private final IPetRepository petRepository;
    private final UserActionValidatorInputBoundary userActionValidator;

    public UserProfileFetcher(IUserRepository userRepository, IPetRepository petRepository,
                              UserActionValidatorInputBoundary userActionValidator) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        this.userActionValidator = userActionValidator;
    }

    /**
     * Fetch a user's profile.
     *
     * @param request Object containing the user's id.
     * @return An object containing the user's profile information.
     */
    @Override
    public ResponseModel fetchUserProfile(UserProfileFetcherRequestModel request) {
        ResponseModel validateActionResponse = userActionValidator.validateAction(new UserActionValidatorRequestModel(
            request.getHeaderUserId(), request.getUserId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int user1Id = Integer.parseInt(request.getHeaderUserId());
        int user2Id = Integer.parseInt(request.getUserId());

        if (!canUserView(user1Id, user2Id)) {
            return new ResponseModel();
        }

        User user = userRepository.fetchUser(user2Id);

        return new ResponseModel(
            true,
            "Successfully fetched user profile.",
            new UserProfileFetcherResponseModel(
                user.getFirstName(),
                user.getLastName(),
                user.getBiography(),
                user.getContactInfo().getPhoneNumber(),
                user.getContactInfo().getEmail(),
                user.getContactInfo().getInstagram(),
                user.getContactInfo().getFacebook()
            )
        );
    }

    /**
     * Return whether user1 is allowed to view user2's profile.
     * True if user1 has *at least one* pet matched with *at least one* of user2's pets.
     * @return whether user1 is allowed to view user2's profile
     */
    public boolean canUserView(int user1Id, int user2Id) {
        if (userRepository.fetchUser(user1Id) == null || userRepository.fetchUser(user2Id) == null) {
            return false;
        }

        if (user1Id == user2Id) {
            return true;
        }

        List<Integer> user2PetIds = userRepository.fetchUserPets(user2Id);

        // Check if user1 has at least one pet that is matched with at least one pet of user2
        for (int user1PetId : userRepository.fetchUserPets(user1Id)) {
            for (int matchedPetId : petRepository.fetchMatches(user1PetId)) {
                if (user2PetIds.contains(matchedPetId)) {
                    return true;
                }
            }
        }

        return false;
    }
}
