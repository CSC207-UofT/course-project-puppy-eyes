package server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface APIGateway {

    /**
     * Create a new user and return a response in the form of a JSON string
     * @param firstName
     * @param lastName
     * @param homeAddress
     * @param password
     * @param email
     * @return user as a JSON string
     * @throws JsonProcessingException
     */
    public String createUser(String firstName, String lastName,
                      String homeAddress, String password, String email);

    public String getUserAccount(String userId);
}