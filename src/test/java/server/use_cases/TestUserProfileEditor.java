package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidator;
import server.use_cases.user_use_cases.user_creator.UserCreator;
import server.use_cases.user_use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_use_cases.user_profile_editor.UserProfileEditor;
import server.use_cases.user_use_cases.user_profile_editor.UserProfileEditorRequestModel;
import server.use_cases.user_use_cases.user_profile_editor.UserProfileEditorResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserProfileEditor {

    private UserProfileEditor profileEditor;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        DummyUserRepository userRepository = new DummyUserRepository();
        UserCreator userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator(), new DummyGeocoderService());

        profileEditor = new UserProfileEditor(userRepository, new UserActionValidator(userRepository));

        // Fill the repository with some dummy users
        userCreator.createUser(new UserCreatorRequestModel("andrew", "qiu", "1234 home st",
                "Toronto", "Password123", "andrew@email.com"));
        userCreator.createUser(new UserCreatorRequestModel("asd", "last", "12345 tom st",
                "Toronto", "Password123", "asd@e.com"));
        userCreator.createUser(new UserCreatorRequestModel("gm", "qw", "45 test st",
                "Toronto", "Password123", "8888@1234.com"));
    }

    /**
     * Test editing a user's profile with a valid id.
     */
    @Test
    public void TestEditUserProfileWithValidId() {
        UserProfileEditorResponseModel expected = new UserProfileEditorResponseModel("1", "Hello", "1231231234", "asd@gmail.com", "asdfacebook");

        ResponseModel responseModel = profileEditor.editUserProfile(
                new UserProfileEditorRequestModel("1", "1", "Hello",
                        "1231231234", "asd@gmail.com", "asdfacebook"));

        assertTrue(responseModel.isSuccess());
        assertEquals(expected, responseModel.getResponseData());
    }

    /**
     * Test editing a user's profile with an invalid id.
     */
    @Test
    public void TestEditUserProfileWithoutValidId() {
        ResponseModel expected = new ResponseModel(false, "User with ID: 3 does not exist.");

        ResponseModel actual = profileEditor.editUserProfile(
                new UserProfileEditorRequestModel("3", "3", "Hello",
                        "1231231234", "asd@gmail.com", "asdfacebook"));

        assertTrue(!actual.isSuccess());
        assertEquals(expected, actual);
    }
}
