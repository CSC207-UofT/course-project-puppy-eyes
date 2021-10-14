public class UserCreatorRequestModel {
    private String name;
    private String emailAddress;
    private String homeAddress;
    private String password;

    public UserCreatorRequestModel(String name, String email, String home, String pass){
        this.name = name;
        this.emailAddress = email;
        this.homeAddress = home;
        this.password = pass;
    }
    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.emailAddress;
    }

    public String getHomeAddress(){
        return this.homeAddress;
    }

    public String getPassword(){
        return this.password;
    }

}
