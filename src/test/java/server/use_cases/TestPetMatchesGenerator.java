package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.drivers.IGeocoderService;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidator;
import server.use_cases.pet_use_cases.pet_creator.PetCreator;
import server.use_cases.pet_use_cases.pet_creator.PetCreatorRequestModel;
import server.use_cases.pet_use_cases.pet_creator.PetCreatorResponseModel;
import server.use_cases.pet_use_cases.pet_matches_generator.PetMatchesGenerator;
import server.use_cases.pet_use_cases.pet_matches_generator.PetMatchesGeneratorRequestModel;
import server.use_cases.pet_use_cases.pet_matches_generator.PetMatchesGeneratorResponseModel;
import server.use_cases.pet_use_cases.pet_profile_validator.PetProfileValidator;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_use_cases.user_creator.UserCreator;
import server.use_cases.user_use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_use_cases.user_creator.UserCreatorResponseModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This Test Unit will fail without the GeocodeApiKey class
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestPetMatchesGenerator {

    private PetMatchesGenerator petMatchesGenerator;

    String user1Id, user2Id, user3Id, pet1Id, pet2Id, pet3Id;

    @BeforeEach
    public void setUp() {
        IGeocoderService geocoderService = new DummyGeocoderService();
        BCryptService bcryptService = new BCryptService();
        DummyUserRepository userRepository = new DummyUserRepository();
        UserCreator userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator(), geocoderService);
        DummyPetRepository petRepository = new DummyPetRepository(userRepository);
        PetCreator petCreator = new PetCreator(petRepository, userRepository, new PetProfileValidator());

        petMatchesGenerator = new PetMatchesGenerator(
                userRepository,
                petRepository,
                new PetActionValidator(petRepository)
        );

        // Create users
        user1Id = ((UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("John",
                "Appleseed", "20 St George Street", "Toronto",
                "Password123", "john.appleseed@email.com")).getResponseData()).getUserId();
        user2Id = ((UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("Mike",
                "Hunter", "21 St George Street", "Toronto", "Password123",
                "mike.the.hunter@email.com")).getResponseData()).getUserId();
        user3Id = ((UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("Hugh",
                "Janus", "1st Avenue", "New York", "Password123",
                "this.janus.is.hugh@email.com")).getResponseData()).getUserId();
        // Create pets
        pet1Id = ((PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(user1Id, user1Id, "Pocky", "5",
                "Golden Retriever", "The happiest dog in the world!")).getResponseData()).getPetId();
        pet2Id = ((PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(user2Id, user2Id, "Jack", "3",
                "German Shepherd", "Certified Good Boy TM.")).getResponseData()).getPetId();
        pet3Id = ((PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(user3Id, user3Id, "Shandra", "2",
                "Chihuahua", "Loves to bite mom's and dad's butts.")).getResponseData()).getPetId();
    }

    /**
     * Test generating a list of potential pet matches for a valid user
     */
    @Test()
    public void TestSuccessPetMatchesGenerator() {
        ResponseModel responseModel = petMatchesGenerator.generatePotentialMatches(new
                PetMatchesGeneratorRequestModel(user1Id, pet1Id));
        List<String> expected = Arrays.asList(pet2Id);
        List<String> actual = ((PetMatchesGeneratorResponseModel) responseModel.getResponseData()).getPetIds();

        // pet3Id should not be in this list since user3 is too far away
        assertTrue(!actual.contains(pet3Id));

        assertEquals(expected, actual);
    }

}
