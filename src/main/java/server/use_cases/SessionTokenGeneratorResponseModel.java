package server.use_cases;

import server.use_cases.repo_abstracts.ResponseData;

public class SessionTokenGeneratorResponseModel extends ResponseData {

    private final String jwt;

    public SessionTokenGeneratorResponseModel(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return this.jwt;
    }

}
