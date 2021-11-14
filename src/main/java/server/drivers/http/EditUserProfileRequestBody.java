package server.drivers.http;

/**
 * Represents an HTTP request body for the "/users/profile-edit" POST route.
 */
public class EditUserProfileRequestBody {
    private final String userId;
    private final String newBiography;
    private final String newPhoneNumber;
    private final String newInstagram;
    private final String newFacebook;

    public EditUserProfileRequestBody(String userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook) {
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
}
