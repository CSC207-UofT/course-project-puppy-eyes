public class UserCreatorRequestModel {
    public String firstName;
    public String lastName;
    public String email;
    public String homeAddress;
    public String password;

    public UserCreatorRequestModel(String firstName, String lastName, String email, String home, String pass){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.homeAddress = home;
        this.password = pass;
    }
}
