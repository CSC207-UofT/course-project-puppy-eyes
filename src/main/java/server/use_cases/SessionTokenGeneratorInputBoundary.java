package server.use_cases;

public interface SessionTokenGeneratorInputBoundary {

    public SessionTokenGeneratorResponseModel generateSessionToken(SessionTokenGeneratorRequestModel request);

}
