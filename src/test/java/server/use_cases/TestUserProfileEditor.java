package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserProfileEditor {
    private static DummyUserRepository dummyUserRepository;
    private static UserProfileEditor profileEditor;
    private static UserCreator userCreator;

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
        dummyUserRepository.createUser("andrew", "qiu", "1234 home st",
                "Toronto", "12345", "andrew@email.com");
        dummyUserRepository.createUser("asd", "last", "12345 tom st",
                "Toronto", "65432", "asd@e.com");
        dummyUserRepository.createUser("gm", "qw", "45 test st",
                "Toronto", "7777", "8888@1234.com");

        UserProfileEditorResponseModel expected = new UserProfileEditorResponseModel(true, "1", "Hello", "1231231234", "asd@gmail.com", "asdfacebook");


        UserProfileEditorResponseModel actual = profileEditor.editUserProfile(
                new UserProfileEditorRequestModel("1", "Hello", "1231231234", "asd@gmail.com", "asdfacebook"));

        assertEquals(expected, actual);
    }

    /**
     * Test editing a user's profile with an invalid id.
     */
    @Test
    public void TestEditUserProfileWithoutValidId() {
        dummyUserRepository.createUser("andrew", "qiu", "1234 home st",
                "Toronto", "12345", "andrew@email.com");
        dummyUserRepository.createUser("asd", "last", "12345 tom st",
                "Toronto", "65432", "asd@e.com");
        dummyUserRepository.createUser("gm", "qw", "45 test st",
                "Toronto", "7777", "8888@1234.com");

        UserProfileEditorResponseModel expected = new UserProfileEditorResponseModel(false, "3", "Hello", "1231231234", "asd@gmail.com", "asdfacebook");


        UserProfileEditorResponseModel actual = profileEditor.editUserProfile(
                new UserProfileEditorRequestModel("3", "Hello", "1231231234", "asd@gmail.com", "asdfacebook"));

        assertEquals(expected, actual);
    }
}
