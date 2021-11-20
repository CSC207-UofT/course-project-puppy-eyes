package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.use_cases.repo_abstracts.ResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserAccountValidator {

    private UserAccountValidator userAccountValidator;

    @BeforeEach
    public void setUp() {
        userAccountValidator = new UserAccountValidator();
    }

    @Test()
    public void TestFailCreateUserNameTooShort() {
        ResponseModel expected = new ResponseModel(false,
                "Please enter a name of at least 2 characters.");

        ResponseModel actual = userAccountValidator.validateAccount(new UserAccountValidatorRequestModel("I",
                "Li", "Toronto", "StrongPassword123", "li@email.com"));

        assertTrue(!actual.isSuccess());
        assertEquals(expected, actual);
    }

    @Test()
    public void TestFailCreateUserPasswordTooShort() {
        ResponseModel expected = new ResponseModel(false,
                "Please enter a password of at least 6 characters.");

        ResponseModel actual = userAccountValidator.validateAccount(new UserAccountValidatorRequestModel("joe",
                "bob", "Toronto", "123", "joe@email.com"));

        assertTrue(!actual.isSuccess());
        assertEquals(expected, actual);
    }

    @Test()
    public void TestFailCreateUserPasswordNoLowercase() {
        ResponseModel expected = new ResponseModel(false, "Please enter a password with at least 1 lowercase letter.");

        ResponseModel actual = userAccountValidator.validateAccount(new UserAccountValidatorRequestModel("joe",
                "bob", "Toronto", "ALL_CAPS_PASSWORD123", "joe@email.com"));

        assertTrue(!actual.isSuccess());
        assertEquals(expected, actual);
    }

    @Test()
    public void TestFailCreateUserPasswordNoUppercase() {
        ResponseModel expected = new ResponseModel(false, "Please enter a password with at least 1 uppercase letter.");

        ResponseModel actual = userAccountValidator.validateAccount(new UserAccountValidatorRequestModel("joe",
                "bob", "Toronto", "all_lower_password123", "joe@email.com"));

        assertTrue(!actual.isSuccess());
        assertEquals(expected, actual);
    }

}
