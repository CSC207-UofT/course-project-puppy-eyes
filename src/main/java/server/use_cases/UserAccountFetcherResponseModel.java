package server.use_cases;

import java.util.Objects;

/**
 * An object defining the response type for
 * UserAccountFetcherInputBoundary.fetchUserAccount
 */
public class UserAccountFetcherResponseModel {
    private final boolean isSuccess;
    private final String firstName;
    private final String lastName;
    private final String homeAddress;
    private final String email;

    public UserAccountFetcherResponseModel(boolean isSuccess, String firstName, String lastName, String homeAddress, String email) {
        this.isSuccess = isSuccess;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.email = email;
    }

    public boolean isSuccess() {
        return isSuccess;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountFetcherResponseModel that = (UserAccountFetcherResponseModel) o;
        return isSuccess == that.isSuccess && Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) && Objects.equals(homeAddress, that.homeAddress) &&
                Objects.equals(email, that.email);
    }
}
