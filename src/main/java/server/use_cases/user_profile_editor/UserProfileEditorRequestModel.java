package server.use_cases.user_profile_editor;

import server.use_cases.AuthRequestModel;

/**
 * An object defining the request type for UserProfileEditor.editUserProfile
 */
public class UserProfileEditorRequestModel extends AuthRequestModel {

    private final String newBiography;
    private final String newPhoneNumber;
    private final String newInstagram;
    private final String newFacebook;

    public UserProfileEditorRequestModel(String headerUserId, String userId, String newBiography, String newPhoneNumber,
                                         String newInstagram, String newFacebook) {
        super(headerUserId, userId);
        this.newBiography = newBiography;
        this.newPhoneNumber = newPhoneNumber;
        this.newInstagram = newInstagram;
        this.newFacebook = newFacebook;
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
}
