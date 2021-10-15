package server;

public class UserAccountFetcherResponseModel {
    private boolean isSuccess;
    private String firstName;
    private String lastName;
    private String homeAddress;
    private String email;

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
}
