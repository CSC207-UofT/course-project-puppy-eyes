package server.use_cases.user_account_editor;

import server.use_cases.repo_abstracts.ResponseData;

import java.util.Objects;

/**
 * An object defining the response type for UserAccountEditor.editUserAccount
 */
public class UserAccountEditorResponseModel extends ResponseData {

    private final String userId;
    private final String newFirstName;
    private final String newLastName;
    private final String newCurrentAddress;
    private final String newCurrentCity;
    private final String newPassword;
    private final String newEmail;

    public UserAccountEditorResponseModel(String userId, String newFirstName, String newLastName, String newCurrentAddress,
                                          String newCurrentCity, String newPassword, String newEmail) {
        this.userId = userId;
        this.newFirstName = newFirstName;
        this.newLastName = newLastName;
        this.newCurrentAddress = newCurrentAddress;
        this.newCurrentCity = newCurrentCity;
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

    public String getNewCurrentAddress() {
        return newCurrentAddress;
    }

    public String getNewCurrentCity() {
        return newCurrentCity;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewEmail() {
        return newEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountEditorResponseModel that = (UserAccountEditorResponseModel) o;
        return  Objects.equals(userId, that.userId)
                && Objects.equals(newFirstName, that.newFirstName)
                && Objects.equals(newLastName, that.newLastName)
                && Objects.equals(newCurrentAddress, that.newCurrentAddress)
                && Objects.equals(newCurrentCity, that.newCurrentCity)
                && Objects.equals(newPassword, that.newPassword)
                && Objects.equals(newEmail, that.newEmail);
    }
}
