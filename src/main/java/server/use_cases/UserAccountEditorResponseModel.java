package server.use_cases;

import java.util.Objects;

/**
 * An object defining the response type for UserAccountEditor.editUserAccount
 */
public class UserAccountEditorResponseModel {
    private final boolean isSuccess;
    private final String userId;
    private final String newFirstName;
    private final String newLastName;
    private final String newAddress;
    private final String newCity;
    private final String newPassword;
    private final String newEmail;

    public UserAccountEditorResponseModel(boolean isSuccess, String userId, String newFirstName, String newLastName, String newAddress, String newCity, String newPassword, String newEmail) {
        this.isSuccess = isSuccess;
        this.userId = userId;
        this.newFirstName = newFirstName;
        this.newLastName = newLastName;
        this.newAddress = newAddress;
        this.newCity = newCity;
        this.newPassword = newPassword;
        this.newEmail = newEmail;
    }

    public boolean isSuccess() {
        return isSuccess;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountEditorResponseModel that = (UserAccountEditorResponseModel) o;
        return isSuccess == that.isSuccess
                && Objects.equals(userId, that.userId)
                && Objects.equals(newFirstName, that.newFirstName)
                && Objects.equals(newLastName, that.newLastName)
                && Objects.equals(newAddress, that.newAddress)
                && Objects.equals(newPassword, that.newPassword)
                && Objects.equals(newEmail, that.newEmail);
    }
}
