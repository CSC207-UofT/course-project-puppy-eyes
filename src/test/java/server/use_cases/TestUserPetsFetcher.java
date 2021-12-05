package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidator;
import server.use_cases.user_use_cases.user_creator.UserCreator;
import server.use_cases.user_use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_use_cases.user_creator.UserCreatorResponseModel;
import server.use_cases.user_use_cases.user_pets_fetcher.UserPetsFetcher;
import server.use_cases.user_use_cases.user_pets_fetcher.UserPetsFetcherRequestModel;
import server.use_cases.user_use_cases.user_pets_fetcher.UserPetsFetcherResponseModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserPetsFetcher {

    private DummyUserRepository userRepository;
    private DummyPetRepository petRepository;
    private UserPetsFetcher userPetsFetcher;

    int user1Id, pet1Id, pet2Id;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        userRepository = new DummyUserRepository();
        petRepository = new DummyPetRepository(userRepository);
        userPetsFetcher = new UserPetsFetcher(userRepository, new UserActionValidator(userRepository));
        UserCreator userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator());

        // Create some users and pets
        user1Id = Integer.parseInt(((UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "John", "Appleseed", "123 Addy", "Toronto",
                        "Password123", "john.appleseed@gmail.com"
                )
        ).getResponseData()).getUserId());

        pet1Id = petRepository.createPet(user1Id, "Nugget", 1, "Golden Shepherd", "A golden boy.");
        pet2Id = petRepository.createPet(user1Id, "Jack", 2, "Husky", "Certified Good Boy.");
    }

    @Test
    public void TestSuccessUserPetsFetcher() {
        ResponseModel response = userPetsFetcher.fetchUserPets(new UserPetsFetcherRequestModel(user1Id + "", user1Id + ""));

        List<String> expected = Arrays.asList("0", "1");
        List<String> actual = ((UserPetsFetcherResponseModel) response.getResponseData()).getPetIds();

        assertTrue(response.isSuccess());
        assertEquals(expected, actual);
    }

    @Test
    public void TestFailUserPetsFetcher() {
        // Test with invalid header user ID
        ResponseModel response = userPetsFetcher.fetchUserPets(new UserPetsFetcherRequestModel("", user1Id + ""));

        assertTrue(!response.isSuccess());
    }

}
