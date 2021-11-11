package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPetProfileFetcher {
    private static DummyPetRepository dummyPetRepository;
    private static PetProfileFetcher petProfileFetcher;

    @BeforeEach
    public void setUp() {
        dummyPetRepository = new DummyPetRepository();
        petProfileFetcher = new PetProfileFetcher(dummyPetRepository);
    }

    @Test
    public void TestFetchPetWithValidId() {
        dummyPetRepository.createPet("Amy", 100, "Turtle", "Ahhhh");
        dummyPetRepository.createPet("Bob", 2, "Dog", "Bobobobobo");
        dummyPetRepository.createPet("Cindy", 7, "Cat", "Meow");

        PetProfileFetcherResponseModel expected = new PetProfileFetcherResponseModel(true, "Cindy", 7, "Cat", "Meow");
        PetProfileFetcherResponseModel actual = petProfileFetcher.fetchPetProfile(new PetProfileFetcherRequestModel("2"));
        assertEquals(expected, actual);
    }

    @Test
    public void TestFetchPetWithoutValidId() {
        dummyPetRepository.createPet("Amy", 100, "Turtle", "Ahhhh");
        dummyPetRepository.createPet("Bob", 2, "Dog", "Bobobobobo");
        dummyPetRepository.createPet("Cindy", 7, "Cat", "Meow");

        PetProfileFetcherResponseModel expected = new PetProfileFetcherResponseModel(false, "", -1, "", "");
        PetProfileFetcherResponseModel actual = petProfileFetcher.fetchPetProfile(new PetProfileFetcherRequestModel("3"));
        assertEquals(expected, actual);
    }
}
