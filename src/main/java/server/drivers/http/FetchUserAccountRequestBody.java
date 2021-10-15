package server.drivers.http;

/**
 * Represents an HTTP request body for the "/users/account" GET route.
 */
public class FetchUserAccountRequestBody {
    private final String userId;

    public FetchUserAccountRequestBody(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
