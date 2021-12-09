package server.drivers.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import server.use_cases.ResponseModel;
import server.adapters.UseCaseOutputBoundary;

public abstract class WebGateway {

    private final UseCaseOutputBoundary presenter;

    public WebGateway(UseCaseOutputBoundary presenter) {
        this.presenter = presenter;
    }

    /**
     * Return the ResponseModel into a ResponseEntity with the proper status code after calling the presenter.
     *
     * @param response
     * @return a ResponseEntity
     */
    public ResponseEntity getResponseEntity(ResponseModel response) {
        String formattedData = presenter.formatResponse(response);

        if (response.isForbidden()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(formattedData);
        }

        if (!response.isSuccess()) {
            return ResponseEntity.badRequest().body(formattedData);
        }

        return ResponseEntity.ok(formattedData);
    }

}
