package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.use_cases.repo_abstracts.ResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserProfileFetcher {

    private UserProfileFetcher userProfileFetcher;
    private DummyUserRepository dummyUserRepository;

    @BeforeEach
    public void setUp() {
        dummyUserRepository = new DummyUserRepository();
        userProfileFetcher = new UserProfileFetcher(dummyUserRepository);
    }

    /**
     * Test fetching a user's profile with a valid id.
     */
    @Test
    public void TestFetchUserProfileWithValidId() {
        dummyUserRepository.createUser("test1", "test2", "123456", "12345 main street",
                "Toronto", "asd@email.com");
        dummyUserRepository.editUserProfile(0, "biography", "111", "insta", "facebook");

        UserProfileFetcherResponseModel expected = new UserProfileFetcherResponseModel("test1", "test2", "biography", "111", "asd@email.com", "insta", "facebook");
        ResponseModel responseModel = userProfileFetcher.fetchUserProfile(new UserProfileFetcherRequestModel("0"));

        UserProfileFetcherResponseModel actual = (UserProfileFetcherResponseModel) responseModel.getResponseData();

        assertTrue(responseModel.isSuccess());
        assertEquals(expected, actual);
    }

    /**
     * Test fetching a user's profile with an invalid id.
     */
    @Test
    public void TestFetchUserProfileWithoutValidId() {
        dummyUserRepository.createUser("andrew", "qiu", "12345", "1234 home st",
                "Toronto", "andrew@email.com");
        dummyUserRepository.editUserProfile(0, "Andrew's biography", "1234567890", "andrewins", "andrewfacebook");
        dummyUserRepository.createUser("asd", "last", "65432", "12345 tom st",
                "Toronto", "asd@e.com");
        dummyUserRepository.editUserProfile(1, "asd's biography", "1111111111", "asdins", "asdfacebook");
        dummyUserRepository.createUser("gm", "qw", "7777", "45 test st",
                "Toronto", "8888@1234.com");

        ResponseModel expected = new ResponseModel(false, "User with ID: 3 does not exist.");
        ResponseModel actual = userProfileFetcher.fetchUserProfile(new UserProfileFetcherRequestModel("3"));

        assertEquals(expected, actual);
    }
}
