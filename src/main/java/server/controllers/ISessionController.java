package server.controllers;

import server.use_cases.repo_abstracts.ResponseModel;

public interface ISessionController {

    /**
     * Generate a JWT for a user given their email and password for authentication.
     *
     * @param email
     * @param password
     * @return a JWT token within a ResponseModel
     */
    public ResponseModel generateJwt(String email, String password);

}