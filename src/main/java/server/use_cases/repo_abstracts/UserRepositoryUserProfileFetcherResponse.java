package server.use_cases.repo_abstracts;

/**
 * A class defining the structure of the IUserRepository response
 * for the fetchUserProfile method.
 */
public class UserRepositoryUserProfileFetcherResponse {
    private final String firstName;
    private final String lastName;
    private final String biography;
    private final String phoneNumber;
    private final String email;
    private final String instagram;
    private final String facebook;

    public UserRepositoryUserProfileFetcherResponse(String firstName, String lastName, String biography,
                                                    String phoneNumber, String email, String instagram, String facebook) {
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
}
