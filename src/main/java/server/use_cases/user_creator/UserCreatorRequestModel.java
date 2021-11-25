package server.use_cases.user_creator;

/**
 * An object defining the request type for
 * UserCreatorInputBoundary.createUser
 */
public class UserCreatorRequestModel {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String currentAddress;
    private final String currentCity;
    private final String password;

    public UserCreatorRequestModel(String firstName, String lastName, String currentAddress, String currentCity, String pass, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.currentAddress = currentAddress;
        this.currentCity = currentCity;
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
