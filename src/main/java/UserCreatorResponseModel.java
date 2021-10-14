public class UserCreatorResponseModel {
    public boolean isSuccess;
    public String firstName;
    public String lastName;
    public String homeAddress;
    public String password;
    public String email;

    public UserCreatorResponseModel(boolean isSuccess, String firstName, String lastName,
                                    String homeAddress, String password, String email) {
        this.isSuccess = isSuccess;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.password = password;
        this.email = email;
    }
}
