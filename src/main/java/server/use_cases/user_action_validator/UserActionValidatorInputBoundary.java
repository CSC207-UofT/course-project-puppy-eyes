package server.use_cases.user_action_validator;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "user action validator" use case.
 */
public interface UserActionValidatorInputBoundary {

    public ResponseModel validateAction(UserActionValidatorRequestModel request);

}
