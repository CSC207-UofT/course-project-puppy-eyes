package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.use_cases.repo_abstracts.ResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserAccountFetcher {

    private UserAccountFetcher userAccountFetcher;
    private DummyUserRepository dummyUserRepository;

    @BeforeEach
    public void setUp() {
        dummyUserRepository = new DummyUserRepository();
        userAccountFetcher = new UserAccountFetcher(dummyUserRepository);
    }

    /**
     * Test fetching a user's account with a valid user id.
     */
    @Test()
    public void TestSuccessFetchUser() {
        // Fill the repository with some dummy users
        dummyUserRepository.createUser("andrew", "qiu", "12345", "1234 home st",
                "Toronto", "andrew@email.com");
        dummyUserRepository.createUser("asd", "last", "65432", "12345 tom st",
                "Toronto", "asd@e.com");
        dummyUserRepository.createUser("gm", "qw", "7777", "45 test st",
                "Toronto", "8888@1234.com");

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
        // Fill the repository with some dummy users
        dummyUserRepository.createUser("andrew", "qiu", "12345", "1234 home st",
                "Toronto", "andrew@email.com");
        dummyUserRepository.createUser("asd", "last", "65432", "12345 tom st",
                "Toronto", "asd@e.com");
        dummyUserRepository.createUser("gm", "qw", "7777", "45 test st",
                "Toronto", "8888@1234.com");

        ResponseModel responseModel = userAccountFetcher.fetchUserAccount(
                new UserAccountFetcherRequestModel("3", "3"));

        assertTrue(!responseModel.isSuccess());
    }
}
