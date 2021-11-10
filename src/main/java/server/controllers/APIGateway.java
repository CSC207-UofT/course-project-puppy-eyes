package server.controllers;

/**
 * An interface representing a connection to some implementation
 * of a back-end API.
 */
public interface APIGateway {

    /**
     * Create a new user and return a response in the form of a JSON string.
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param homeAddress the user's home address
     * @param password the user's password
     * @param email the user's email
     */
    public String createUser(String firstName, String lastName,
                      String homeAddress, String password, String email);

    public String fetchUserAccount(String userId);

    public String createPet(String name, int age);
}