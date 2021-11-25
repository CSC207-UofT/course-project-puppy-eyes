package server.use_cases.user_account_validator;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "user credentials validator" use case.
 */
public interface UserAccountValidatorInputBoundary {

    public ResponseModel validateAccount(UserAccountValidatorRequestModel request);

}
