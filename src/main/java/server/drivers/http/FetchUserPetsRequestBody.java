package server.drivers.http;

/**
 * Represents an HTTP request body for the "/users/fetchpets" GET route.
 */
public class FetchUserPetsRequestBody {
    private final int userId;

    public FetchUserPetsRequestBody(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
