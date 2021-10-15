public interface APIGateway<RequestType>{
    /* Create a new user and return a response in the form of a JSON string.*/
    String createUser(RequestType request);
}
