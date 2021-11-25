package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.use_cases.user_account_editor.UserAccountEditor;
import server.use_cases.user_account_editor.UserAccountEditorRequestModel;
import server.use_cases.user_account_editor.UserAccountEditorResponseModel;
import server.use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_creator.UserCreator;
import server.use_cases.user_creator.UserCreatorRequestModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserAccountEditor {

    private DummyUserRepository dummyUserRepository;
    private UserAccountEditor accountEditor;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        UserAccountValidator userAccountValidator = new UserAccountValidator();

        dummyUserRepository = new DummyUserRepository();
        accountEditor = new UserAccountEditor(dummyUserRepository, bcryptService, userAccountValidator);
        UserCreator userCreator = new UserCreator(dummyUserRepository, bcryptService, userAccountValidator);

        // Create some users
        userCreator.createUser(new UserCreatorRequestModel("andrew", "qiu", "1234 home st",
                "Toronto", "Password123", "andrew@email.com"));
        userCreator.createUser(new UserCreatorRequestModel("asd", "last", "12345 tom st",
                "Toronto", "Password123", "asd@e.com"));
        userCreator.createUser(new UserCreatorRequestModel("gm", "qw", "45 test st",
                "Toronto", "Password123", "8888@1234.com"));
    }

    /**
     * Test editing a user's account with a valid id.
     */
    @Test
    public void TestEditUserAccountWithValidId() {
        UserAccountEditorResponseModel expected = new UserAccountEditorResponseModel("1",
                "Bob", "Boy", "111 Main St", "New York",
                "NewPassword123", "b.b@gmail,com");

        ResponseModel responseModel = accountEditor.editUserAccount(
                new UserAccountEditorRequestModel("1", "1", "Bob", "Boy",
                        "111 Main St", "New York", "NewPassword123", "b.b@gmail,com"));

        assertTrue(responseModel.isSuccess());
        assertEquals(expected, responseModel.getResponseData());
    }

    /**
     * Test editing a user's account with an invalid id.
     */
    @Test
    public void TestEditUserAccountWithoutValidId() {
        ResponseModel expected = new ResponseModel(false, "User with ID: 3 does not exist.");

        ResponseModel actual = accountEditor.editUserAccount(
                new UserAccountEditorRequestModel("3", "3", "Bob", "Boy",
                        "111 Main St", "New York", "NewPassword123", "b.b@gmail,com"));

        assertTrue(!actual.isSuccess());
        assertEquals(expected, actual);
    }
}
