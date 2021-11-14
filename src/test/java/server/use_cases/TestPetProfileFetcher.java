package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.use_cases.repo_abstracts.ResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestPetProfileFetcher {

    private DummyPetRepository dummyPetRepository;
    private PetProfileFetcher petProfileFetcher;
    private UserCreator userCreator;

    @BeforeEach
    public void setUp() {
        userCreator = new UserCreator(new DummyUserRepository());
        dummyPetRepository = new DummyPetRepository();
        petProfileFetcher = new PetProfileFetcher(dummyPetRepository);
    }

    @Test
    public void TestFetchPetWithValidId() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = (UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("John", "Appleseed", "20 St George Street",
                "Toronto", "123456", "john.appleseed@gmail.com")).getResponseData();

        int userId = Integer.parseInt(userCreatorResponse.getUserId());

        dummyPetRepository.createPet(userId,"Amy", 100, "Turtle", "Ahhhh");
        dummyPetRepository.createPet(userId,"Bob", 2, "Dog", "Bobobobobo");
        dummyPetRepository.createPet(userId,"Cindy", 7, "Cat", "Meow");

        PetProfileFetcherResponseModel expected = new PetProfileFetcherResponseModel("Cindy",
                7, "Cat", "Meow");
        ResponseModel responseModel = petProfileFetcher.fetchPetProfile(
                new PetProfileFetcherRequestModel(String.valueOf(userId), "2")
        );

        assertEquals(expected, responseModel.getResponseData());
    }

    @Test
    public void TestFetchPetWithoutValidId() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = (UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("John", "Appleseed", "20 St George Street",
                "Toronto", "123456", "john.appleseed@gmail.com")).getResponseData();

        int userId = Integer.parseInt(userCreatorResponse.getUserId());

        dummyPetRepository.createPet(userId,"Amy", 100, "Turtle", "Ahhhh");
        dummyPetRepository.createPet(userId,"Bob", 2, "Dog", "Bobobobobo");
        dummyPetRepository.createPet(userId,"Cindy", 7, "Cat", "Meow");

        int nonExistentId = 3;

        ResponseModel expected = new ResponseModel(false, "Pet with ID: " + nonExistentId + " does not exist.");
        ResponseModel actual = petProfileFetcher.fetchPetProfile(new PetProfileFetcherRequestModel(String.valueOf(userId), nonExistentId + ""));

        assertEquals(expected, actual);
    }
}
