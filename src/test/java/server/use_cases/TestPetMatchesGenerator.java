package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.use_cases.repo_abstracts.ResponseModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestPetMatchesGenerator {

    private UserCreator userCreator;
    private PetCreator petCreator;
    private PetMatchesGenerator petMatchesGenerator;

    @BeforeEach
    public void setUp() {
        DummyUserRepository userRepository = new DummyUserRepository();
        userCreator = new UserCreator(userRepository);
        DummyPetRepository petRepository = new DummyPetRepository(userRepository);
        petCreator = new PetCreator(petRepository, userRepository);

        petMatchesGenerator = new PetMatchesGenerator(userRepository, petRepository);
    }

    /**
     * Test generating a list of potential pet matches for a valid user
     */
    @Test()
    public void TestSuccessPetMatchesGenerator() {
        // Create users
        String user1Id = ((UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("John",
                "Appleseed", "20 St George Street", "Toronto",
            "123456", "john.appleseed@email.com")).getResponseData()).getUserId();
        String user2Id = ((UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("Mike",
                "Hunter", "21 St George Street", "Toronto", "123456",
                "mike.the.hunter@email.com")).getResponseData()).getUserId();
        // Create pets
        String pet1Id = ((PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(user1Id, user1Id, "Pocky", 5,
                "Golden Retriever", "The happiest dog in the world!")).getResponseData()).getPetId();
        String pet2Id = ((PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(user2Id, user2Id, "Jack", 3,
                "German Shepherd", "Certified Good Boy TM.")).getResponseData()).getPetId();

        ResponseModel responseModel = petMatchesGenerator.generatePotentialMatches(new
                PetMatchesGeneratorRequestModel(user1Id, pet1Id));
        List<String> expected = Arrays.asList("1");
        List<String> actual = ((PetMatchesGeneratorResponseModel) responseModel.getResponseData()).getPotentialMatches();

        assertEquals(expected, actual);
    }

}
