package server.drivers.http.requestBody;

/**
 * Represents an HTTP request body for the "/auth/login" POST route.
 */
public class LoginRequestBody {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequestBody(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
