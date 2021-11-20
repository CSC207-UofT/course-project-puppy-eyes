package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.use_cases.repo_abstracts.ResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserCreator {

    private UserCreator userCreator;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        userCreator = new UserCreator(new DummyUserRepository(), bcryptService, new UserAccountValidator());
    }

    @Test()
    public void TestSuccessCreateUser() {
        UserCreatorResponseModel expected = new UserCreatorResponseModel("0", "joe",
                "bob", "8888 Joe St", "Toronto", "joe@email.com");

        ResponseModel responseModel = userCreator.createUser(new UserCreatorRequestModel("joe",
                "bob", "8888 Joe St", "Toronto", "Password123", "joe@email.com"));

        UserCreatorResponseModel actual = (UserCreatorResponseModel) responseModel.getResponseData();

        assertTrue(responseModel.isSuccess());
        assertEquals(expected, actual);
    }

    // For test cases on validation, see TestUserAccountValidator
}
