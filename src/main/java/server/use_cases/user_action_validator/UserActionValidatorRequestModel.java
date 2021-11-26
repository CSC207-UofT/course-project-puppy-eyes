package server.use_cases.user_action_validator;

import server.use_cases.AuthRequestModel;

public class UserActionValidatorRequestModel extends AuthRequestModel {

    public UserActionValidatorRequestModel(boolean fromTerminal, String headerUserId, String userId) {
        super(headerUserId, userId);
        this.setFromTerminal(fromTerminal);
    }

}
