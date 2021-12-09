package server.adapters;

import server.use_cases.ResponseModel;

public interface UseCaseOutputBoundary {

    String formatResponse(ResponseModel response);

}
