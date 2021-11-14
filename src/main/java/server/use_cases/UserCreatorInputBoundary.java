package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

/**
 * An input boundary for the "create user" use case.
 */
public interface UserCreatorInputBoundary {

    public ResponseModel createUser(UserCreatorRequestModel request);

}
