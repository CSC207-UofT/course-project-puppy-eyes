package server.drivers.http.requestBody;

/**
 * Represents an HTTP request body for the "/users/account-edit" POST route.
 */
public class EditUserAccountRequestBody {
    private final String userId;
    private final String newFirstName;
    private final String newLastName;
    private final String newAddress;
    private final String newCity;
    private final String newPassword;
    private final String newEmail;

    public EditUserAccountRequestBody(String userId, String newFirstName, String newLastName, String newAddress,
                                      String newCity, String newPassword, String newEmail) {
        this.userId = userId;
        this.newFirstName = newFirstName;
        this.newLastName = newLastName;
        this.newAddress = newAddress;
        this.newCity = newCity;
        this.newPassword = newPassword;
        this.newEmail = newEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public String getNewCity() {
        return newCity;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewEmail() {
        return newEmail;
    }
}