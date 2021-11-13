package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserAccountEditor {
    private static DummyUserRepository dummyUserRepository;
    private static UserAccountEditor accountEditor;
    private static UserCreator userCreator;

    @BeforeEach
    public void setUp() {
        DummyUserRepository userRepository = new DummyUserRepository();
        dummyUserRepository = new DummyUserRepository();

        userCreator = new UserCreator(userRepository);
        accountEditor = new UserAccountEditor(dummyUserRepository);
    }

    /**
     * Test editing a user's account with a valid id.
     */
    @Test
    public void TestEditUserAccountWithValidId() {
        dummyUserRepository.createUser("andrew", "qiu", "1234 home st",
                "Toronto", "12345", "andrew@email.com");
        dummyUserRepository.createUser("asd", "last", "12345 tom st",
                "Toronto", "65432", "asd@e.com");
        dummyUserRepository.createUser("gm", "qw", "45 test st",
                "Toronto", "7777", "8888@1234.com");

        UserAccountEditorResponseModel expected = new UserAccountEditorResponseModel(true,"1",
                "Bob", "Boy", "111 Main St", "New York",
                "22222", "b.b@gmail,com");

        UserAccountEditorResponseModel actual = accountEditor.editUserAccount(
                new UserAccountEditorRequestModel("1", "Bob", "Boy",
                        "111 Main St", "New York", "22222", "b.b@gmail,com"));

        assertEquals(expected, actual);
    }

    /**
     * Test editing a user's account with an invalid id.
     */
    @Test
    public void TestEditUserAccountWithoutValidId() {
        dummyUserRepository.createUser("andrew", "qiu", "1234 home st",
                "Toronto", "12345", "andrew@email.com");
        dummyUserRepository.createUser("asd", "last", "12345 tom st",
                "Toronto", "65432", "asd@e.com");
        dummyUserRepository.createUser("gm", "qw", "45 test st",
                "Toronto", "7777", "8888@1234.com");

        UserAccountEditorResponseModel expected = new UserAccountEditorResponseModel(false,"3",
                "Bob", "Boy", "111 Main St", "New York",
                "22222", "b.b@gmail,com");

        UserAccountEditorResponseModel actual = accountEditor.editUserAccount(
                new UserAccountEditorRequestModel("3", "Bob", "Boy",
                        "111 Main St", "New York", "22222", "b.b@gmail,com"));

        assertEquals(expected, actual);
    }
}
