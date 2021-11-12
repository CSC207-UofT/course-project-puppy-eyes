package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPetProfileFetcher {
    private static DummyPetRepository dummyPetRepository;
    private static PetProfileFetcher petProfileFetcher;
    private static UserCreator userCreator;

    @BeforeEach
    public void setUp() {
        userCreator = new UserCreator(new DummyUserRepository());
        dummyPetRepository = new DummyPetRepository();
        petProfileFetcher = new PetProfileFetcher(dummyPetRepository);
    }

    @Test
    public void TestFetchPetWithValidId() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = userCreator.createUser(new UserCreatorRequestModel("John", "Appleseed", "20 St George Street",
                "Toronto", "123456", "john.appleseed@gmail.com"));

        int userId = Integer.parseInt(userCreatorResponse.getUserId());

        dummyPetRepository.createPet(userId,"Amy", 100, "Turtle", "Ahhhh");
        dummyPetRepository.createPet(userId,"Bob", 2, "Dog", "Bobobobobo");
        dummyPetRepository.createPet(userId,"Cindy", 7, "Cat", "Meow");

        PetProfileFetcherResponseModel expected = new PetProfileFetcherResponseModel(true, "Cindy",
                7, "Cat", "Meow");
        PetProfileFetcherResponseModel actual = petProfileFetcher.fetchPetProfile(
                new PetProfileFetcherRequestModel("2"));

        assertEquals(expected, actual);
    }

    @Test
    public void TestFetchPetWithoutValidId() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = userCreator.createUser(new UserCreatorRequestModel("John", "Appleseed", "20 St George Street",
                "Toronto", "123456", "john.appleseed@gmail.com"));

        int userId = Integer.parseInt(userCreatorResponse.getUserId());

        dummyPetRepository.createPet(userId,"Amy", 100, "Turtle", "Ahhhh");
        dummyPetRepository.createPet(userId,"Bob", 2, "Dog", "Bobobobobo");
        dummyPetRepository.createPet(userId,"Cindy", 7, "Cat", "Meow");

        PetProfileFetcherResponseModel expected = new PetProfileFetcherResponseModel(false, "",
                -1, "", "");
        PetProfileFetcherResponseModel actual = petProfileFetcher.fetchPetProfile(
                new PetProfileFetcherRequestModel("3"));

        assertEquals(expected, actual);
    }
}
