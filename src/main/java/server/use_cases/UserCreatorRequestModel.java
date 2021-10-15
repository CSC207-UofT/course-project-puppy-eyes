package server.use_cases;

/**
 * An object defining the request type for
 * UserCreatorInputBoundary.createUser
 */
public class UserCreatorRequestModel {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String homeAddress;
    private final String password;

    public UserCreatorRequestModel(String firstName, String lastName, String homeAddress, String pass, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.homeAddress = homeAddress;
        this.password = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getHomeAddress(){
        return this.homeAddress;
    }

    public String getPassword(){
        return this.password;
    }

}
