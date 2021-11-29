package server.use_cases.user_use_cases.session_token_generator;

import server.use_cases.ResponseData;

public class SessionTokenGeneratorResponseModel extends ResponseData {

    private final String jwt;

    public SessionTokenGeneratorResponseModel(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return this.jwt;
    }

}
