package server.use_cases.repo_abstracts;

/**
 * A class defining the structure of the IUserRepository response
 * for the fetchUserAccount method.
 */
public class UserRepositoryUserAccountFetcherResponse {
    private final String firstName;
    private final String lastName;
    private final String homeAddress;
    private final String email;

    public UserRepositoryUserAccountFetcherResponse(String firstName, String lastName, String homeAddress, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getEmail() {
        return email;
    }
}
