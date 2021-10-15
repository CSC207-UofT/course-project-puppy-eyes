package cupet;

// Defines the methods that let us interact with the database
public interface UserRepositoryInterface {

    /**
     * Create a new user and save it in the database
     * @param firstName
     * @param lastName
     * @param homeAddress
     * @param password
     * @param email
     */
    public void createUser(String firstName, String lastName, String homeAddress, String password, String email);

}