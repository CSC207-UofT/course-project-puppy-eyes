package server.drivers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import server.adapters.UseCaseOutputBoundary;
import server.drivers.http.UserWebGateway;
import server.drivers.http.requestBody.*;
import server.drivers.mocks.MockUserController;

import javax.servlet.http.HttpServletRequest;

/**
 * Test the UserWebGateway. This class is non-functional, so we test that no runtime exceptions are thrown.
 */
public class TestUserWebGateway {
    public UserWebGateway userWebGateway;
    public HttpServletRequest req;

    @BeforeEach
    public void setUp() {
        UseCaseOutputBoundary outputBoundary = Mockito.mock(UseCaseOutputBoundary.class);

        req = Mockito.mock(HttpServletRequest.class);
        userWebGateway = new UserWebGateway(new MockUserController(), outputBoundary);
    }

    @Test
    public void testCreateUser() {
        userWebGateway.createUser(new CreateUserRequestBody(
                "test first", "test last", "test email",
                "test current address", "test city", "test password"));
    }

    @Test
    public void testGetUserAccount() {
        userWebGateway.getUserAccount(req, "test id");
    }

    @Test
    public void testEditUserAccount() {
        userWebGateway.editUserAccount(req, new EditUserAccountRequestBody(
                "test id", "test name", "test last",
                "test address", "test city",
                "test password", "test email"));
    }

    @Test
    public void testEditProfileImage() {
        userWebGateway.editProfileImage(req, new SetUserProfileImageRequestBody("test b64"));
    }

    @Test
    public void testGetUserProfile() {
        userWebGateway.getUserProfile(req, "test user id");

    }

    @Test
    public void testEditUserProfile() {
        userWebGateway.editUserProfile(req, new EditUserProfileRequestBody(
                "test id", "test bio",
                "test phone", "test insta",
                "test facebook"));
    }

    @Test
    public void testFetchUserPets() {
        userWebGateway.fetchUserPets(req, "test id");
    }
}
