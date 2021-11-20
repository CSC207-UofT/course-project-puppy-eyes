package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

/**
 * An input boundary for the "user credentials validator" use case.
 */
public interface UserAccountValidatorInputBoundary {

    public ResponseModel validateAccount(UserAccountValidatorRequestModel request);

}
