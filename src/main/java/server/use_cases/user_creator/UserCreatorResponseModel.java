package server.use_cases.user_creator;

import server.use_cases.ResponseData;

import java.util.Objects;

/**
 * An object defining the response type for
 * UserCreatorInputBoundary.createUser
 */
public class UserCreatorResponseModel extends ResponseData {

    private final String firstName;
    private final String lastName;
    private final String currentAddress;
    private final String currentCity;
    private final String email;
    private final String userId;

    public UserCreatorResponseModel(String userId, String firstName, String lastName, String currentAddress, String currentCity,
                                    String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentAddress = currentAddress;
        this.currentCity = currentCity;
        this.email = email;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreatorResponseModel that = (UserCreatorResponseModel) o;
        return  Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(currentAddress, that.currentAddress)
                && Objects.equals(currentCity, that.currentCity)
                && Objects.equals(email, that.email)
                && Objects.equals(userId, that.userId);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCurrentAddress(){
        return this.currentAddress;
    }

    public String getCurrentCity(){
        return this.currentCity;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }
}
