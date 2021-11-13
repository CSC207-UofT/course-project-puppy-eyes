package server.drivers.http;

/**
 * Represents an HTTP request body for the "/users/profile" GET route.
 */
public class FetchUserProfileRequestBody {
    private final String userId;

    public FetchUserProfileRequestBody(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
