package server.controllers;

import server.use_cases.SessionTokenGeneratorInputBoundary;
import server.use_cases.SessionTokenGeneratorRequestModel;
import server.use_cases.SessionTokenGeneratorResponseModel;

public class SessionController implements ISessionController {

    public SessionTokenGeneratorInputBoundary sessionTokenGenerator;

    public SessionController(SessionTokenGeneratorInputBoundary sessionTokenGenerator) {
        this.sessionTokenGenerator = sessionTokenGenerator;
    }

    @Override
    public String generateJwt(String email, String password) {
        SessionTokenGeneratorRequestModel request = new SessionTokenGeneratorRequestModel(email, password);
        SessionTokenGeneratorResponseModel response = sessionTokenGenerator.generateSessionToken(request);

        if (!response.isSuccess()) {
            return "null";
        }

        return response.getJwt();
    }


}