package server.use_cases.user_use_cases.user_account_validator;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "user credentials validator" use case.
 */
public interface UserAccountValidatorInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request
     * @return a ResponseModel object
     */
    ResponseModel validateAccount(UserAccountValidatorRequestModel request);

}
