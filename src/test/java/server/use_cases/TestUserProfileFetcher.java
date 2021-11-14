package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserProfileFetcher {
    private static UserProfileFetcher userProfileFetcher;
    private static DummyUserRepository dummyUserRepository;

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
        dummyUserRepository.createUser("andrew", "qiu", "1234 home st",
                "Toronto", "12345", "andrew@email.com");
        dummyUserRepository.editUserProfile(0, "Andrew's biography", "1234567890", "andrewins", "andrewfacebook");
        dummyUserRepository.createUser("asd", "last", "12345 tom st",
                "Toronto", "65432", "asd@e.com");
        dummyUserRepository.editUserProfile(1, "asd's biography", "1111111111", "asdins", "asdfacebook");
        dummyUserRepository.createUser("gm", "qw", "45 test st",
                "Toronto", "7777", "8888@1234.com");

        UserProfileFetcherResponseModel expected = new UserProfileFetcherResponseModel(true, "andrew", "qiu", "Andrew's biography", "1234567890", "andrew@email.com", "andrewins", "andrewfacebook");
        UserProfileFetcherResponseModel actual = userProfileFetcher.fetchUserProfile(new UserProfileFetcherRequestModel("0"));

        assertEquals(expected, actual);
    }

    /**
     * Test fetching a user's profile with an invalid id.
     */
    @Test
    public void TestFetchUserProfileWithoutValidId() {
        dummyUserRepository.createUser("andrew", "qiu", "1234 home st",
                "Toronto", "12345", "andrew@email.com");
        dummyUserRepository.editUserProfile(0, "Andrew's biography", "1234567890", "andrewins", "andrewfacebook");
        dummyUserRepository.createUser("asd", "last", "12345 tom st",
                "Toronto", "65432", "asd@e.com");
        dummyUserRepository.editUserProfile(1, "asd's biography", "1111111111", "asdins", "asdfacebook");
        dummyUserRepository.createUser("gm", "qw", "45 test st",
                "Toronto", "7777", "8888@1234.com");

        UserProfileFetcherResponseModel expected = new UserProfileFetcherResponseModel(false, "", "", "", "", "", "", "");
        UserProfileFetcherResponseModel actual = userProfileFetcher.fetchUserProfile(new UserProfileFetcherRequestModel("3"));

        assertEquals(expected, actual);
    }
}
