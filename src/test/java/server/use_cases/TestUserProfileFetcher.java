package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_creator.UserCreator;
import server.use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_creator.UserCreatorResponseModel;
import server.use_cases.user_profile_fetcher.UserProfileFetcher;
import server.use_cases.user_profile_fetcher.UserProfileFetcherRequestModel;
import server.use_cases.user_profile_fetcher.UserProfileFetcherResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserProfileFetcher {

    private UserProfileFetcher userProfileFetcher;

    private int userId;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        DummyUserRepository userRepository = new DummyUserRepository();
        userProfileFetcher = new UserProfileFetcher(userRepository);

        UserCreator userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator());

        // Fill the repository with some dummy users
        userId = Integer.parseInt(((UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "John", "Appleseed", "123 Addy", "Toronto",
                        "Password123", "john.appleseed@gmail.com"
                )
        ).getResponseData()).getUserId());

        userRepository.editUserProfile(userId, "John's Biography", "1234567890", "therealjohnappleseed", "johnappleseedofficial");
    }

    /**
     * Test fetching a user's profile with a valid id.
     */
    @Test
    public void TestFetchUserProfileWithValidId() {
        UserProfileFetcherResponseModel expected = new UserProfileFetcherResponseModel("John", "Appleseed", "John's Biography", "1234567890", "john.appleseed@gmail.com", "therealjohnappleseed", "johnappleseedofficial");
        ResponseModel responseModel = userProfileFetcher.fetchUserProfile(new UserProfileFetcherRequestModel(userId + ""));

        UserProfileFetcherResponseModel actual = (UserProfileFetcherResponseModel) responseModel.getResponseData();

        assertTrue(responseModel.isSuccess());
        assertEquals(expected, actual);
    }

    /**
     * Test fetching a user's profile with an invalid id.
     */
    @Test
    public void TestFetchUserProfileWithoutValidId() {
        ResponseModel expected = new ResponseModel(false, "User with ID: 3 does not exist.");
        ResponseModel actual = userProfileFetcher.fetchUserProfile(new UserProfileFetcherRequestModel("3"));

        assertEquals(expected, actual);
    }
}
