package server;

public class UserRepositoryUserAccountFetcherResponse {
    private String firstName;
    private String lastName;
    private String homeAddress;
    private String email;

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
