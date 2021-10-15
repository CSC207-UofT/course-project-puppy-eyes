package cupet;

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
                             String homeAddress, String password, String email) throws JsonProcessingException;

//    /**
//     * Return a List of JSON strings each representing a user
//     * @return a List of JSON strings
//     */
//    public List<String> getAllUsers() throws JsonProcessingException;
}