package server.use_cases.user_use_cases.user_action_validator;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "user action validator" use case.
 */
public interface UserActionValidatorInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request the request object
     * @return a ResponseModel object
     */
    ResponseModel validateAction(UserActionValidatorRequestModel request);

}
