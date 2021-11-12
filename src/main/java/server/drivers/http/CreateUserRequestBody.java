package server.drivers.http;

/**
 * Represents an HTTP request body for the "/users/create" POST route.
 */
public class CreateUserRequestBody {

    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String currentAddress;
    private final String currentCity;
    private final String password;

    public CreateUserRequestBody(String firstName, String lastName, String email, String currentAddress,
                                 String currentCity, String pass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = email;
        this.currentAddress = currentAddress;
        this.currentCity = currentCity;
        this.password = pass;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmailAddress(){
        return this.emailAddress;
    }

    public String getCurrentAddress(){
        return this.currentAddress;
    }

    public String getCurrentCity(){
        return this.currentCity;
    }

    public String getPassword(){
        return this.password;
    }

}