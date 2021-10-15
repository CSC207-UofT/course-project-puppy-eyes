package server.drivers.http;

public class FetchUserAccountRequestBody {
    private String userId;

    public FetchUserAccountRequestBody(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
