package server.use_cases;

public class SessionTokenGeneratorResponseModel {

    private final String jwt;
    private final boolean isSuccess;

    public SessionTokenGeneratorResponseModel(String jwt, boolean isSuccess) {
        this.jwt = jwt;
        this.isSuccess = isSuccess;
    }

    public String getJwt() {
        return this.jwt;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }
}
