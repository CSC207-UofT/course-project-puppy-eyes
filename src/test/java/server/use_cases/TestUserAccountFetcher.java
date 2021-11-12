package server.use_cases;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.controllers.IJSONPresenter;
import server.controllers.JSONPresenter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserAccountFetcher {
    private static UserAccountFetcher userAccountFetcher;
    private static DummyUserRepository dummyUserRepository;

    @BeforeEach
    public void setUp() {
        dummyUserRepository = new DummyUserRepository();
        userAccountFetcher = new UserAccountFetcher(dummyUserRepository);
    }

    /**
     * Test fetching a user with a valid user id.
     */
    @Test()
    public void TestSuccessFetchUser() {
        // Fill the repository with some dummy users
        dummyUserRepository.createUser("andrew", "qiu", "1234 home st",
                "Toronto", "12345", "andrew@email.com");
        dummyUserRepository.createUser("asd", "last", "12345 tom st",
                "Toronto", "65432", "asd@e.com");
        dummyUserRepository.createUser("gm", "qw", "45 test st",
                "Toronto", "7777", "8888@1234.com");

        UserAccountFetcherResponseModel expected = new UserAccountFetcherResponseModel(true, "asd",
                "last", "12345 tom st", "Toronto", "asd@e.com");

        UserAccountFetcherResponseModel actual = userAccountFetcher.fetchUserAccount(
                new UserAccountFetcherRequestModel("1"));

        assertEquals(expected, actual);
    }

    /**
     * Test fetching a user with an invalid id.
     */
    @Test()
    public void TestFailFetchUser() {
        // Fill the repository with some dummy users
        dummyUserRepository.createUser("andrew", "qiu", "1234 home st",
                "Toronto", "12345", "andrew@email.com");
        dummyUserRepository.createUser("asd", "last", "12345 tom st",
                "Toronto", "65432", "asd@e.com");
        dummyUserRepository.createUser("gm", "qw", "45 test st",
                "Toronto", "7777", "8888@1234.com");

        UserAccountFetcherResponseModel expected = new UserAccountFetcherResponseModel(false, "",
                "", "", "", "");

        UserAccountFetcherResponseModel actual = userAccountFetcher.fetchUserAccount(
                new UserAccountFetcherRequestModel("3"));

        assertEquals(expected, actual);
    }
}
