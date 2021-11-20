package server.adapters;

import server.use_cases.ResponseModel;

public interface UseCaseOutputBoundary {

    public String formatResponse(ResponseModel response);

}
