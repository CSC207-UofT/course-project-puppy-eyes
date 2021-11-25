package server.drivers.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.controllers.ISessionController;
import server.drivers.http.requestBody.LoginRequestBody;
import server.use_cases.ResponseModel;
import server.adapters.UseCaseOutputBoundary;

@RestController
@RequestMapping("/auth")
public class AuthWebGateway extends WebGateway {

    private final ISessionController sessionController;

    public AuthWebGateway(ISessionController sessionController, UseCaseOutputBoundary presenter) {
        super(presenter);
        this.sessionController = sessionController;
    }

    @PostMapping("/login")
    public ResponseEntity generateJwt(@RequestBody LoginRequestBody loginRequest) {
        ResponseModel response = sessionController.generateJwt(loginRequest.getEmail(), loginRequest.getPassword());
        return getResponseEntity(response);
    }


}
