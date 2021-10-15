package server.use_cases;

import java.util.Objects;

/**
 * An object defining the response type for
 * UserCreatorInputBoundary.createUser
 */
public class UserCreatorResponseModel {

    private final boolean isSuccess;
    private final String firstName;
    private final String lastName;
    private final String homeAddress;
    private final String email;
    private final String userId;

    public UserCreatorResponseModel(boolean isSuccess, String firstName, String lastName,
                                    String homeAddress, String email, String userId) {
        this.isSuccess = isSuccess;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.email = email;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreatorResponseModel that = (UserCreatorResponseModel) o;
        return isSuccess == that.isSuccess && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(homeAddress, that.homeAddress)
                && Objects.equals(email, that.email)
                && Objects.equals(userId, that.userId);
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

    public String getUserId() {
        return userId;
    }
}
