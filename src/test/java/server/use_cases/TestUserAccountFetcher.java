package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.use_cases.repo_abstracts.ResponseModel;
import server.use_cases.user_account_fetcher.UserAccountFetcher;
import server.use_cases.user_account_fetcher.UserAccountFetcherRequestModel;
import server.use_cases.user_account_fetcher.UserAccountFetcherResponseModel;
import server.use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_creator.UserCreator;
import server.use_cases.user_creator.UserCreatorRequestModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserAccountFetcher {

    private UserAccountFetcher userAccountFetcher;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        DummyUserRepository userRepository = new DummyUserRepository();
        userAccountFetcher = new UserAccountFetcher(userRepository);
        UserCreator userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator());

        // Fill the repository with some dummy users
        userCreator.createUser(new UserCreatorRequestModel("andrew", "qiu", "1234 home st",
                "Toronto", "Password123", "andrew@email.com"));
        userCreator.createUser(new UserCreatorRequestModel("asd", "last", "12345 tom st",
                "Toronto", "Password123", "asd@e.com"));
        userCreator.createUser(new UserCreatorRequestModel("gm", "qw", "45 test st",
                "Toronto", "Password123", "8888@1234.com"));
    }

    /**
     * Test fetching a user's account with a valid user id.
     */
    @Test()
    public void TestSuccessFetchUser() {
        UserAccountFetcherResponseModel expected = new UserAccountFetcherResponseModel("asd",
                "last", "12345 tom st", "Toronto", "asd@e.com");

        ResponseModel responseModel = userAccountFetcher.fetchUserAccount(
                new UserAccountFetcherRequestModel("1", "1"));
        UserAccountFetcherResponseModel responseData = (UserAccountFetcherResponseModel) responseModel.getResponseData();

        assertTrue(responseModel.isSuccess());
        assertEquals(expected, responseData);
    }

    /**
     * Test fetching a user's account with an invalid id.
     */
    @Test()
    public void TestFailFetchUser() {
        ResponseModel responseModel = userAccountFetcher.fetchUserAccount(
                new UserAccountFetcherRequestModel("3", "3"));

        assertTrue(!responseModel.isSuccess());
    }
}
