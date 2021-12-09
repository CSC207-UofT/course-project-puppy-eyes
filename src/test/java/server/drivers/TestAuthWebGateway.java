package server.drivers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import server.adapters.UseCaseOutputBoundary;
import server.controllers.SessionController;
import server.drivers.http.AuthWebGateway;
import server.drivers.http.requestBody.LoginRequestBody;
import server.drivers.mocks.MockSessionController;

/**
 * Test the AuthWebGateway. This class is non-functional, so we test that no runtime exceptions are thrown.
 */
public class TestAuthWebGateway {
    public AuthWebGateway authWebGateway;
    public SessionController sessionController;

    @BeforeEach
    public void setUp() {
        UseCaseOutputBoundary outputBoundary = Mockito.mock(UseCaseOutputBoundary.class);
        authWebGateway = new AuthWebGateway(new MockSessionController(), outputBoundary);
    }

    @Test
    public void testGenerateJwt() {
        authWebGateway.generateJwt(new LoginRequestBody("a.z@gmail.com", "123456"));
    }

}
