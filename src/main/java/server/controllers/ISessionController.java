package server.controllers;

import server.use_cases.ResponseModel;

public interface ISessionController {

    /**
     * Generate a JWT for a user given their email and password for authentication.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return a JWT token within a ResponseModel
     */
    ResponseModel generateJwt(String email, String password);

}