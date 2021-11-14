package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.drivers.JwtService;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSessionTokenGenerator {

    private static SessionTokenGenerator sessionTokenGenerator;
    private static UserCreator userCreator;
    private static JwtService jwtService;

    @BeforeEach
    public void setUp() {
        DummyUserRepository userRepository = new DummyUserRepository();
        userCreator = new UserCreator(userRepository);
        jwtService = new JwtService();
        sessionTokenGenerator = new SessionTokenGenerator(userRepository, jwtService);
    }

    /**
     * Test generating a session token for a valid user.
     */
    @Test()
    public void TestSuccessSessionTokenGenerator() {
        String email = "john.appleseed@email.com";

        UserCreatorResponseModel userCreatorResponse = userCreator.createUser(new UserCreatorRequestModel("John",
                "Appleseed", "20 St George Street", "Toronto", "123456", email));

        SessionTokenGeneratorResponseModel response = sessionTokenGenerator.generateSessionToken(
                new SessionTokenGeneratorRequestModel(email, "123456")
        );

        assertTrue(response.isSuccess());
        assertTrue(jwtService.validateToken(response.getJwt(), userCreatorResponse.getUserId()));
    }

    /**
     * Test generating a session token for a non-existent user.
     */
    @Test()
    public void TestFailNonExistingUserSessionTokenGenerator() {
        SessionTokenGeneratorResponseModel response = sessionTokenGenerator.generateSessionToken(
                new SessionTokenGeneratorRequestModel("fake@email.com", "123456")
        );

        assertTrue(!response.isSuccess());
        assertTrue(response.getJwt() == null);
    }

    /**
     * Test generating a session token for an existing user, but entering incorrect credentials.
     */
    @Test()
    public void TestFailIncorrectCredentialsSessionTokenGenerator() {
        userCreator.createUser(new UserCreatorRequestModel("John", "Appleseed",
                "20 St George Street", "Toronto", "123456", "john.appleseed@email.com"));

        SessionTokenGeneratorResponseModel response = sessionTokenGenerator.generateSessionToken(
                new SessionTokenGeneratorRequestModel("fake@email.com", "654321")
        );

        assertTrue(!response.isSuccess());
        assertTrue(response.getJwt() == null);
    }

}