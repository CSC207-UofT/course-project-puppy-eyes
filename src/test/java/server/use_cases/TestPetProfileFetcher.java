package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.use_cases.pet_use_cases.pet_profile_fetcher.PetProfileFetcher;
import server.use_cases.pet_use_cases.pet_profile_fetcher.PetProfileFetcherRequestModel;
import server.use_cases.pet_use_cases.pet_profile_fetcher.PetProfileFetcherResponseModel;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_use_cases.user_creator.UserCreator;
import server.use_cases.user_use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_use_cases.user_creator.UserCreatorResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestPetProfileFetcher {

    private DummyPetRepository dummyPetRepository;
    private PetProfileFetcher petProfileFetcher;

    private int userId;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        DummyUserRepository userRepository = new DummyUserRepository();
        UserCreator userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator(), new DummyGeocoderService());
        dummyPetRepository = new DummyPetRepository(userRepository);
        petProfileFetcher = new PetProfileFetcher(dummyPetRepository, new DummyImageRepository());

        // Create some users
        UserCreatorResponseModel userCreatorResponse = (UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "John", "Appleseed", "20 St George Street",
                        "Toronto", "Password123", "john.appleseed@gmail.com"
                )
        ).getResponseData();

        userId = Integer.parseInt(userCreatorResponse.getUserId());

        // Create some pets
        dummyPetRepository.createPet(userId, "Amy", 100, "Turtle", "Ahhhh");
        dummyPetRepository.createPet(userId, "Bob", 2, "Dog", "Bobobobobo");
        dummyPetRepository.createPet(userId, "Cindy", 7, "Cat", "Meow");
    }

    @Test
    public void TestFetchPetWithValidId() {
        PetProfileFetcherResponseModel expected = new PetProfileFetcherResponseModel(userId, "Cindy",
                7, "Cat", "Meow", "");
        ResponseModel responseModel = petProfileFetcher.fetchPetProfile(
                new PetProfileFetcherRequestModel("2")
        );

        assertEquals(expected, responseModel.getResponseData());
    }

    @Test
    public void TestFetchPetWithoutValidId() {
        int nonExistentId = 3;

        ResponseModel expected = new ResponseModel(false, "Pet with ID: " + nonExistentId + " does not exist.");
        ResponseModel actual = petProfileFetcher.fetchPetProfile(new PetProfileFetcherRequestModel(nonExistentId + ""));

        assertEquals(expected, actual);
    }
}
