package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.use_cases.repo_abstracts.ResponseModel;
import server.use_cases.repo_abstracts.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserAccountEditor {

    private DummyUserRepository dummyUserRepository;
    private UserAccountEditor accountEditor;

    @BeforeEach
    public void setUp() {
        dummyUserRepository = new DummyUserRepository();
        accountEditor = new UserAccountEditor(dummyUserRepository);
    }

    /**
     * Test editing a user's account with a valid id.
     */
    @Test
    public void TestEditUserAccountWithValidId() {
        dummyUserRepository.createUser("andrew", "qiu", "12345", "1234 home st",
                "Toronto", "andrew@email.com");
        dummyUserRepository.createUser("asd", "last", "65432", "12345 tom st",
                "Toronto", "asd@e.com");
        dummyUserRepository.createUser("gm", "qw", "7777", "45 test st",
                "Toronto", "8888@1234.com");

        UserAccountEditorResponseModel expected = new UserAccountEditorResponseModel("1",
                "Bob", "Boy", "111 Main St", "New York",
                "22222", "b.b@gmail,com");

        ResponseModel responseModel = accountEditor.editUserAccount(
                new UserAccountEditorRequestModel("1", "1", "Bob", "Boy",
                        "111 Main St", "New York", "22222", "b.b@gmail,com"));

        assertTrue(responseModel.isSuccess());
        assertEquals(expected, responseModel.getResponseData());
    }

    /**
     * Test editing a user's account with an invalid id.
     */
    @Test
    public void TestEditUserAccountWithoutValidId() {
        dummyUserRepository.createUser("andrew", "qiu", "12345", "1234 home st",
                "Toronto", "andrew@email.com");
        dummyUserRepository.createUser("asd", "last", "65432", "12345 tom st",
                "Toronto", "asd@e.com");
        dummyUserRepository.createUser("gm", "qw", "7777", "45 test st",
                "Toronto", "8888@1234.com");

        ResponseModel expected = new ResponseModel(false, "User with ID: 3 does not exist.");

        ResponseModel actual = accountEditor.editUserAccount(
                new UserAccountEditorRequestModel("3", "3", "Bob", "Boy",
                        "111 Main St", "New York", "22222", "b.b@gmail,com"));

        assertTrue(!actual.isSuccess());
        assertEquals(expected, actual);
    }
}
