package server.drivers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import server.adapters.UseCaseOutputBoundary;
import server.drivers.http.MainWebGateway;

/**
 * Test the MainWebGateway. This class is non-functional, so we test that no runtime exceptions are thrown.
 */
public class TestMainWebGateway {
    public MainWebGateway mainWebGateway;
    public HttpServletRequest req;

    @BeforeEach
    public void setUp() {
        UseCaseOutputBoundary outputBoundary = Mockito.mock(UseCaseOutputBoundary.class);

        req = Mockito.mock(HttpServletRequest.class);
        mainWebGateway = new MainWebGateway();
    }

    @Test
    public void TestLandingMessage() {
        mainWebGateway.landingMessage();
    }

    @Test
    public void TestAuthTest() {
        mainWebGateway.authTest(req);
    }
}
