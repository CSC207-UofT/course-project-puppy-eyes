package server.use_cases;

public class SessionTokenGeneratorRequestModel {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public SessionTokenGeneratorRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

}