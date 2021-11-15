package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.JwtService;
import server.use_cases.repo_abstracts.ResponseModel;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestSessionTokenGenerator {

    private SessionTokenGenerator sessionTokenGenerator;
    private UserCreator userCreator;
    private JwtService jwtService;

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

        UserCreatorResponseModel userCreatorResponse = (UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("John",
                "Appleseed", "20 St George Street", "Toronto", "123456", email)).getResponseData();

        ResponseModel response = sessionTokenGenerator.generateSessionToken(
                new SessionTokenGeneratorRequestModel(email, "123456")
        );

        SessionTokenGeneratorResponseModel responseData = (SessionTokenGeneratorResponseModel) response.getResponseData();

        assertTrue(response.isSuccess());
        assertTrue(jwtService.validateToken(responseData.getJwt(), userCreatorResponse.getUserId()));
    }

    /**
     * Test generating a session token for a non-existent user.
     */
    @Test()
    public void TestFailNonExistingUserSessionTokenGenerator() {
        ResponseModel response = sessionTokenGenerator.generateSessionToken(
                new SessionTokenGeneratorRequestModel("fakeemail@gmail.com", "123456")
        );

        SessionTokenGeneratorResponseModel responseData = (SessionTokenGeneratorResponseModel) response.getResponseData();

        assertTrue(!response.isSuccess());
        assertTrue(responseData == null);
    }

    /**
     * Test generating a session token for an existing user, but entering incorrect credentials.
     */
    @Test()
    public void TestFailIncorrectCredentialsSessionTokenGenerator() {
        userCreator.createUser(new UserCreatorRequestModel("John", "Appleseed",
                "20 St George Street", "Toronto", "123456", "john.appleseed@email.com"));

        ResponseModel response = sessionTokenGenerator.generateSessionToken(
                new SessionTokenGeneratorRequestModel("john.appleseed@gmail.com", "654321")
        );
        SessionTokenGeneratorResponseModel responseData = (SessionTokenGeneratorResponseModel) response.getResponseData();

        assertTrue(!response.isSuccess());
        assertTrue(responseData == null);
    }

}