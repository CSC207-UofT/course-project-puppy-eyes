package server.use_cases;

import server.controllers.IJSONPresenter;
import server.adapters.UseCaseOutputBoundary;

import java.util.HashMap;

/**
 * An implementation of UseCaseOutputBoundary whose goal is to format a ResponseModel into
 * a String
 */
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