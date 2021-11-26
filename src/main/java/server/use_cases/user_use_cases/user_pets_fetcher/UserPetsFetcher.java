package server.use_cases.user_use_cases.user_pets_fetcher;

import server.use_cases.ResponseModel;
import server.use_cases.repo_abstracts.*;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidatorInputBoundary;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidatorRequestModel;

import java.util.List;
import java.util.stream.Collectors;

public class UserPetsFetcher implements UserPetsFetcherInputBoundary {

    private final IUserRepository userRepository;
    private final UserActionValidatorInputBoundary userActionValidator;

    public UserPetsFetcher(IUserRepository userRepository, UserActionValidatorInputBoundary userActionValidator) {
        this.userRepository = userRepository;
        this.userActionValidator = userActionValidator;
    }

    /**
     * Create a new ResponseModel with given request.
     *
     * @param request Object containing id of the user.
     * @return a ResponseModel listing all the pets.
     */
    @Override
    public ResponseModel fetchUserPets(UserPetsFetcherRequestModel request)  {
        ResponseModel validateActionResponse = userActionValidator.validateAction(new UserActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getUserId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int userId = Integer.parseInt(request.getUserId());

        // Convert integers to strings
        List<String> stringPetIds = userRepository.fetchUserPets(userId).stream().
                map(String::valueOf).
                collect(Collectors.toList());

        return new ResponseModel(
            true,
            "Successfully fetched user pets.",
            new UserPetsFetcherResponseModel(stringPetIds)
        );
    }

}
