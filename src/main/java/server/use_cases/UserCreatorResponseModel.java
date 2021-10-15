package server.use_cases;

public class UserCreatorResponseModel {

    private boolean isSuccess;
    private String firstName;
    private String lastName;
    private String homeAddress;
    private String email;
    private String userId;

    public UserCreatorResponseModel(boolean isSuccess, String firstName, String lastName,
                                    String homeAddress, String email, String userId) {
        this.isSuccess = isSuccess;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.email = email;
        this.userId = userId;
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
