package server.controllers;

import server.use_cases.session_token_generator.SessionTokenGeneratorInputBoundary;
import server.use_cases.session_token_generator.SessionTokenGeneratorRequestModel;
import server.use_cases.ResponseModel;

public class SessionController implements ISessionController {

    public SessionTokenGeneratorInputBoundary sessionTokenGenerator;

    public SessionController(SessionTokenGeneratorInputBoundary sessionTokenGenerator) {
        this.sessionTokenGenerator = sessionTokenGenerator;
    }

    @Override
    public ResponseModel generateJwt(String email, String password) {
        SessionTokenGeneratorRequestModel request = new SessionTokenGeneratorRequestModel(email, password);
        ResponseModel response = sessionTokenGenerator.generateSessionToken(request);
        return response;
    }


}