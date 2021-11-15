package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.use_cases.repo_abstracts.ResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserProfileEditor {

    private DummyUserRepository dummyUserRepository;
    private UserProfileEditor profileEditor;
    private UserCreator userCreator;

    @BeforeEach
    public void setUp() {
        DummyUserRepository userRepository = new DummyUserRepository();
        dummyUserRepository = new DummyUserRepository();

        userCreator = new UserCreator(userRepository);
        profileEditor = new UserProfileEditor(dummyUserRepository);
    }

    /**
     * Test editing a user's profile with a valid id.
     */
    @Test
    public void TestEditUserProfileWithValidId() {
        dummyUserRepository.createUser("andrew", "qiu", "12345", "1234 home st",
                "Toronto", "andrew@email.com");
        dummyUserRepository.createUser("asd", "last", "65432", "12345 tom st",
                "Toronto", "asd@e.com");
        dummyUserRepository.createUser("gm", "qw", "7777", "45 test st",
                "Toronto", "8888@1234.com");

        UserProfileEditorResponseModel expected = new UserProfileEditorResponseModel("1", "Hello", "1231231234", "asd@gmail.com", "asdfacebook");

        ResponseModel responseModel = profileEditor.editUserProfile(
                new UserProfileEditorRequestModel("1", "1", "Hello", "1231231234", "asd@gmail.com", "asdfacebook"));

        assertTrue(responseModel.isSuccess());
        assertEquals(expected, responseModel.getResponseData());
    }

    /**
     * Test editing a user's profile with an invalid id.
     */
    @Test
    public void TestEditUserProfileWithoutValidId() {
        dummyUserRepository.createUser("andrew", "qiu", "12345", "1234 home st",
                "Toronto", "andrew@email.com");
        dummyUserRepository.createUser("asd", "last", "65432", "12345 tom st",
                "Toronto", "asd@e.com");
        dummyUserRepository.createUser("gm", "qw", "7777", "45 test st",
                "Toronto", "8888@1234.com");

        ResponseModel expected = new ResponseModel(false, "User with ID: 3 does not exist.");

        ResponseModel actual = profileEditor.editUserProfile(
                new UserProfileEditorRequestModel("3", "3","Hello", "1231231234", "asd@gmail.com", "asdfacebook"));

        assertTrue(!actual.isSuccess());
        assertEquals(expected, actual);
    }
}
