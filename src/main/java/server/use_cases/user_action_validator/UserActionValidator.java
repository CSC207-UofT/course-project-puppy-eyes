package server.use_cases.user_action_validator;

import server.use_cases.ResponseModel;
import server.use_cases.Util;
import server.use_cases.repo_abstracts.IUserRepository;

public class UserActionValidator implements UserActionValidatorInputBoundary {

    private IUserRepository userRepository;

    public UserActionValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseModel validateAction(UserActionValidatorRequestModel request) {
        // null checks
        if (request.getUserId() == null || request.getHeaderUserId() == null) {
            return new ResponseModel(false, "Missing required fields.");
        }

        // Check if the request fields are in the valid datatype
        if (!Util.isInteger(request.getUserId()) || !Util.isInteger(request.getHeaderUserId())) {
            return new ResponseModel(false, "ID must be an integer.");
        }

        int userId = Integer.parseInt(request.getUserId());

        if (userRepository.fetchUser(userId) == null) {
            return new ResponseModel(false, "User with ID: " + request.getUserId() + " does not exist.");
        }

        if (!request.isRequestAuthorized()) {
            return new ResponseModel();
        }

        return new ResponseModel(true, "Action validated.");
    }

}
