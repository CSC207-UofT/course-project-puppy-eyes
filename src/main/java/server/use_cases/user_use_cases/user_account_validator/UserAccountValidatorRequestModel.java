package server.use_cases.user_use_cases.user_account_validator;

/**
 * An object defining the request type for
 * UserCredentialsValidatorInputBoundary.validateCredentials
 */
public class UserAccountValidatorRequestModel {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String currentCity;
    private final String password;

    public UserAccountValidatorRequestModel(String firstName, String lastName, String currentCity, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.currentCity = currentCity;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCurrentCity() {
        return this.currentCity;
    }

    public String getPassword() {
        return this.password;
    }

}
