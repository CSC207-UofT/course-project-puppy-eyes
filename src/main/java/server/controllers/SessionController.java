package server.controllers;

import server.use_cases.SessionTokenGeneratorInputBoundary;
import server.use_cases.SessionTokenGeneratorRequestModel;
import server.use_cases.repo_abstracts.ResponseModel;

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