package server.controllers;

public interface ISessionController {

    /**
     * Generate a JWT for a user given their email and password for authentication
     * @param email
     * @param password
     * @return a JWT token as a String
     */
    public String generateJwt(String email, String password);

}