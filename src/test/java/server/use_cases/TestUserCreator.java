package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.use_cases.repo_abstracts.ResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserCreator {

    private UserCreator userCreator;

    @BeforeEach
    public void setUp() {
        userCreator = new UserCreator(new DummyUserRepository());
    }

    @Test()
    public void TestSuccessCreateUser() {
        UserCreatorResponseModel expected = new UserCreatorResponseModel("0", "joe",
                "bob", "8888 Joe St", "Toronto", "joe@email.com");

        ResponseModel responseModel = userCreator.createUser(new UserCreatorRequestModel("joe",
                "bob", "8888 Joe St", "Toronto", "123456", "joe@email.com"));

        UserCreatorResponseModel actual = (UserCreatorResponseModel) responseModel.getResponseData();

        assertTrue(responseModel.isSuccess());
        assertEquals(expected, actual);
    }

    @Test()
    public void TestFailCreateUserNameTooShort() {
        ResponseModel expected = new ResponseModel(false, "Please enter a name of at least 3 characters.");

        ResponseModel actual = userCreator.createUser(new UserCreatorRequestModel("ye",
                "bob", "8888 Joe St", "Toronto", "123456", "joe@email.com"));

        assertTrue(!actual.isSuccess());
        assertEquals(expected, actual);
    }

    @Test()
    public void TestFailCreateUserPasswordTooShort() {
        ResponseModel expected = new ResponseModel(false, "Please enter a password of at least 6 characters.");

        ResponseModel actual = userCreator.createUser(new UserCreatorRequestModel("joe",
                "bob", "8888 Joe St", "Toronto", "123", "joe@email.com"));

        assertTrue(!actual.isSuccess());
        assertEquals(expected, actual);
    }
}
