package server.use_cases;

public class UserAccountFetcherRequestModel {
    private final String userId;

    public UserAccountFetcherRequestModel(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
