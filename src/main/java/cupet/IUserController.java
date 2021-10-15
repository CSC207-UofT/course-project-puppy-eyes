package cupet;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IUserController {
    /**
     * Create a new user and return a response in the form of a JSON string
     * @param firstName
     * @param lastName
     * @param homeAddress
     * @param password
     * @param email
     * @return
     * @throws JsonProcessingException
     */
    String createUser(String firstName, String lastName,
                                        String homeAddress, String password, String email) throws JsonProcessingException;
}
