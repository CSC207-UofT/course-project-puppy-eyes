package server.use_cases.user_creator;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "create user" use case.
 */
public interface UserCreatorInputBoundary {

    public ResponseModel createUser(UserCreatorRequestModel request);

}
