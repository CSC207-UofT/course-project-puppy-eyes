package server.controllers;

import server.use_cases.SessionTokenGeneratorInputBoundary;
import server.use_cases.SessionTokenGeneratorRequestModel;
import server.use_cases.SessionTokenGeneratorResponseModel;
import server.use_cases.repo_abstracts.ResponseModel;
import server.use_cases.repo_abstracts.ResponsePresenter;
import server.use_cases.repo_abstracts.UseCaseOutputBoundary;

public class SessionController implements ISessionController {

    public SessionTokenGeneratorInputBoundary sessionTokenGenerator;
    public UseCaseOutputBoundary responsePresenter;

    public SessionController(SessionTokenGeneratorInputBoundary sessionTokenGenerator, IJSONPresenter jsonPresenter) {
        this.sessionTokenGenerator = sessionTokenGenerator;
        this.responsePresenter = new ResponsePresenter(jsonPresenter);
    }

    @Override
    public String generateJwt(String email, String password) {
        SessionTokenGeneratorRequestModel request = new SessionTokenGeneratorRequestModel(email, password);
        ResponseModel response = sessionTokenGenerator.generateSessionToken(request);
        return responsePresenter.formatResponse(response);
    }


}