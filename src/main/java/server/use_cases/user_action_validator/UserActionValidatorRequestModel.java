package server.use_cases.user_action_validator;

import server.use_cases.AuthRequestModel;

public class UserActionValidatorRequestModel extends AuthRequestModel {

    public UserActionValidatorRequestModel(String headerUserId, String userId) {
        super(headerUserId, userId);
    }

}
