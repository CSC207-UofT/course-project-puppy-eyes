public class UserCreatorRequestModel {
    private String firstName;
    private String lastName;
    private String email;
    private String homeAddress;
    private String password;

    public UserCreatorRequestModel(String firstName, String lastName, String email, String home, String pass){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.homeAddress = home;
        this.password = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getHomeAddress(){
        return this.homeAddress;
    }

    public String getPassword(){
        return this.password;
    }

}
