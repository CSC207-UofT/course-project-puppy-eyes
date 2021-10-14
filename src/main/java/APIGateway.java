public interface APIGateway {
    /* Create a new user and return a response in the form of a JSON string.*/
    String createUser(String firstName, String lastName,
                                        String homeAddress, String password, String email);
}
