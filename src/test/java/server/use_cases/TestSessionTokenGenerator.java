package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.drivers.JwtService;
import server.use_cases.repo_abstracts.ResponseModel;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestSessionTokenGenerator {

    private SessionTokenGenerator sessionTokenGenerator;
    private JwtService jwtService;

    private String userId;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        DummyUserRepository userRepository = new DummyUserRepository();
        UserCreator userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator());
        jwtService = new JwtService(true);
        sessionTokenGenerator = new SessionTokenGenerator(userRepository, jwtService, bcryptService);

        // Generate some users
        userId = ((UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "John", "Appleseed", "123 Addy", "Toronto",
                        "Password123", "john.appleseed@gmail.com"
                )
        ).getResponseData()).getUserId();
    }

    /**
     * Test generating a session token for a valid user.
     */
    @Test()
    public void TestSuccessSessionTokenGenerator() {
        String email = "john.appleseed@gmail.com";

        ResponseModel response = sessionTokenGenerator.generateSessionToken(
                new SessionTokenGeneratorRequestModel(email, "Password123")
        );

        System.out.println(response.getMessage());

        SessionTokenGeneratorResponseModel responseData = (SessionTokenGeneratorResponseModel) response.getResponseData();

        assertTrue(response.isSuccess());
        assertTrue(jwtService.validateToken(responseData.getJwt(), userId));
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
        ResponseModel response = sessionTokenGenerator.generateSessionToken(
                new SessionTokenGeneratorRequestModel("john.appleseed@gmail.com", "WrongPassword123")
        );
        SessionTokenGeneratorResponseModel responseData = (SessionTokenGeneratorResponseModel) response.getResponseData();

        assertTrue(!response.isSuccess());
        assertTrue(responseData == null);
    }

}