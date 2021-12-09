package server.use_cases.user_use_cases.session_token_generator;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "session token generator" input boundary
 */
public interface SessionTokenGeneratorInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request
     * @return a ResponseModel object
     */
    ResponseModel generateSessionToken(SessionTokenGeneratorRequestModel request);

}
