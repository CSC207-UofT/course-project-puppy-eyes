package server.use_cases.user_use_cases.user_creator;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "create user" use case.
 */
public interface UserCreatorInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request the request object
     * @return a ResponseModel object
     */
    ResponseModel createUser(UserCreatorRequestModel request);

}
