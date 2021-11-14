package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

public interface SessionTokenGeneratorInputBoundary {

    public ResponseModel generateSessionToken(SessionTokenGeneratorRequestModel request);

}
