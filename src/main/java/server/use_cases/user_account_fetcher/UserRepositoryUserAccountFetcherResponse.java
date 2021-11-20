package server.use_cases.user_account_fetcher;

/**
 * A class defining the structure of the IUserRepository response
 * for the fetchUserAccount method.
 */
public class UserRepositoryUserAccountFetcherResponse {
    private final String firstName;
    private final String lastName;
    private final String currentAddress;
    private final String currentCity;
    private final String email;

    public UserRepositoryUserAccountFetcherResponse(String firstName, String lastName, String currentAddress, String currentCity, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentAddress = currentAddress;
        this.currentCity = currentCity;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public String getEmail() {
        return email;
    }
}
