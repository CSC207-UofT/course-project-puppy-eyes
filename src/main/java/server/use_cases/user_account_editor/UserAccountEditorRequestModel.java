package server.use_cases.user_account_editor;

import server.use_cases.AuthRequestModel;

/**
 * An object defining the request type for UserAccountEditor.editUserAccount
 */
public class UserAccountEditorRequestModel extends AuthRequestModel {

    private final String newFirstName;
    private final String newLastName;
    private final String newAddress;
    private final String newCity;
    private final String newPassword;
    private final String newEmail;

    public UserAccountEditorRequestModel(String headerUserId, String userId, String newFirstName, String newLastName, String newAddress, String newCity, String newPassword, String newEmail) {
        super(headerUserId, userId);
        this.newFirstName = newFirstName;
        this.newLastName = newLastName;
        this.newAddress = newAddress;
        this.newCity = newCity;
        this.newPassword = newPassword;
        this.newEmail = newEmail;
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
