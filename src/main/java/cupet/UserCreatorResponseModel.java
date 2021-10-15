public class UserCreatorResponseModel {
    private boolean isSuccess;
    private String firstName;
    private String lastName;
    private String homeAddress;
    private String password;
    private String email;

    public UserCreatorResponseModel(boolean isSuccess, String firstName, String lastName,
                                    String homeAddress, String password, String email) {
        this.isSuccess = isSuccess;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
