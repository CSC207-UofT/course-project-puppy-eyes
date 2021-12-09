package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.drivers.LatLng;
import server.entities.User;
import server.use_cases.user_use_cases.user_account_editor.UserAccountEditor;
import server.use_cases.user_use_cases.user_account_editor.UserAccountEditorRequestModel;
import server.use_cases.user_use_cases.user_account_editor.UserAccountEditorResponseModel;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidator;
import server.use_cases.user_use_cases.user_creator.UserCreator;
import server.use_cases.user_use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_use_cases.user_creator.UserCreatorResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserAccountEditor {

    private DummyUserRepository dummyUserRepository;
    private UserAccountEditor accountEditor;

    private String user1Id, user2Id, user3Id;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        UserAccountValidator userAccountValidator = new UserAccountValidator();

        dummyUserRepository = new DummyUserRepository();
        accountEditor = new UserAccountEditor(dummyUserRepository, bcryptService, userAccountValidator, new UserActionValidator(dummyUserRepository), new DummyGeocoderService());
        UserCreator userCreator = new UserCreator(dummyUserRepository, bcryptService, userAccountValidator, new DummyGeocoderService());

        // Create some users
        user1Id = ((UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("andrew", "qiu", "1234 home st",
                "Toronto", "Password123", "andrew@email.com")).getResponseData()).getUserId();
        user2Id = ((UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("asd", "last", "12345 tom st",
                "Toronto", "Password123", "asd@e.com")).getResponseData()).getUserId();
        user3Id = ((UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("gm", "qw", "45 test st",
                "Toronto", "Password123", "8888@1234.com")).getResponseData()).getUserId();
    }

    /**
     * Test editing a user's account with a valid id.
     */
    @Test
    public void TestEditUserAccountWithValidId() {
        UserAccountEditorResponseModel expected = new UserAccountEditorResponseModel("1",
                "Bob", "Boy", "111 Main St", "New York", "b.b@gmail,com");

        ResponseModel responseModel = accountEditor.editUserAccount(
                new UserAccountEditorRequestModel(user2Id, user2Id, "Bob", "Boy",
                        "111 Main St", "New York", "NewPassword123", "b.b@gmail,com"));

        assertTrue(responseModel.isSuccess());
        assertEquals(expected, responseModel.getResponseData());
    }

    /**
     * Test editing a user's account with a valid id, changing the city to generate a new lat/lng pair.
     */
    @Test
    public void TestEditUserAccountWithValidIdChangeCity() {
        UserAccountEditorResponseModel expected = new UserAccountEditorResponseModel(user2Id, "asd", "last",
                "12345 tom st", "New York", "asd@e.com");

        ResponseModel responseModel = accountEditor.editUserAccount(
                new UserAccountEditorRequestModel(user2Id, user2Id, null, null, null,
                        "New York", null, null));

        // Check that the lat/lng has updated
        User user = dummyUserRepository.fetchUser(Integer.parseInt(user2Id));
        LatLng userLatLng = new LatLng(Double.parseDouble(user.getLat()), Double.parseDouble(user.getLng()));
        LatLng newYorkLatLng = new LatLng(40.7128, 74.0060);

        System.out.println(userLatLng);
        System.out.println(newYorkLatLng);

        assertTrue(userLatLng.equals(newYorkLatLng));
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
                new UserAccountEditorRequestModel(user1Id, user1Id, "Bob", "Boy",
                        "111 Main St", "New York", "NewPassword123", "b.b@gmail,com"));

        assertTrue(!actual.isSuccess());
        assertEquals(expected, actual);
    }
}
