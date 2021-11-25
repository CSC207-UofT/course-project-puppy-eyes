package server.use_cases.session_token_generator;

import server.use_cases.ResponseModel;

public interface SessionTokenGeneratorInputBoundary {

    public ResponseModel generateSessionToken(SessionTokenGeneratorRequestModel request);

}
