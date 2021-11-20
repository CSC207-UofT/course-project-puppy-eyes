package server.use_cases.user_profile_editor;

import server.use_cases.repo_abstracts.ResponseData;

import java.util.Objects;

/**
 * An object defining the response type for UserProfileEditor.editUserProfile
 */
public class UserProfileEditorResponseModel extends ResponseData {

    private final String userId;
    private final String newBiography;
    private final String newPhoneNumber;
    private final String newInstagram;
    private final String newFacebook;

    public UserProfileEditorResponseModel(String userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook) {
        this.userId = userId;
        this.newBiography = newBiography;
        this.newPhoneNumber = newPhoneNumber;
        this.newInstagram = newInstagram;
        this.newFacebook = newFacebook;
    }

    public String getUserId() {
        return userId;
    }

    public String getNewBiography() {
        return newBiography;
    }

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public String getNewInstagram() {
        return newInstagram;
    }

    public String getNewFacebook() {
        return newFacebook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileEditorResponseModel that = (UserProfileEditorResponseModel) o;
        return  Objects.equals(userId, that.userId)
                && Objects.equals(newBiography, that.newBiography)
                && Objects.equals(newPhoneNumber, that.newPhoneNumber)
                && Objects.equals(newInstagram, that.newInstagram)
                && Objects.equals(newFacebook, that.newFacebook);
    }
}
