package server.use_cases;

import server.use_cases.repo_abstracts.ResponseData;

import java.util.Objects;

/**
 * An object defining the response type for
 * UserProfileFetcherInputBoundary.fetchUserProfile
 */
public class UserProfileFetcherResponseModel extends ResponseData {

    private final String firstName;
    private final String lastName;
    private final String biography;
    private final String phoneNumber;
    private final String email;
    private final String instagram;
    private final String facebook;

    public UserProfileFetcherResponseModel(String firstName, String lastName, String biography, String phoneNumber,
                                           String email, String instagram, String facebook) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.instagram = instagram;
        this.facebook = facebook;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBiography() {
        return biography;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileFetcherResponseModel that = (UserProfileFetcherResponseModel) o;
        return  Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(biography, that.biography)
                && Objects.equals(phoneNumber, that.phoneNumber)
                && Objects.equals(email, that.email)
                && Objects.equals(instagram, that.instagram)
                && Objects.equals(facebook, that.facebook);
    }
}
