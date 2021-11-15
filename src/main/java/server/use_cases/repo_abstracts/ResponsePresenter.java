package server.use_cases.repo_abstracts;

import server.controllers.IJSONPresenter;

import java.util.HashMap;

public class ResponsePresenter implements UseCaseOutputBoundary {

    IJSONPresenter jsonPresenter;

    public ResponsePresenter(IJSONPresenter jsonPresenter) {
        this.jsonPresenter = jsonPresenter;
    }

    @Override
    public String formatResponse(ResponseModel response) {
        HashMap<String, String> responseMap = new HashMap<>() {{
            put("isSuccess", String.valueOf(response.isSuccess()));
            put("message", String.valueOf(response.getMessage()));
            put("data", jsonPresenter.toJSON(response.getResponseData()));
        }};

        return jsonPresenter.toJSON(responseMap);
    }

}