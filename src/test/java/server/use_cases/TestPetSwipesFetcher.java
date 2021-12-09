package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidator;
import server.use_cases.pet_use_cases.pet_creator.PetCreator;
import server.use_cases.pet_use_cases.pet_creator.PetCreatorRequestModel;
import server.use_cases.pet_use_cases.pet_creator.PetCreatorResponseModel;
import server.use_cases.pet_use_cases.pet_profile_validator.PetProfileValidator;
import server.use_cases.pet_use_cases.pet_swipes_fetcher.PetSwipesFetcher;
import server.use_cases.pet_use_cases.pet_swipes_fetcher.PetSwipesFetcherRequestModel;
import server.use_cases.pet_use_cases.pet_swipes_fetcher.PetSwipesFetcherResponseModel;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_use_cases.user_creator.UserCreator;
import server.use_cases.user_use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_use_cases.user_creator.UserCreatorResponseModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestPetSwipesFetcher {

    private DummyPetRepository dummyPetRepository;
    private PetSwipesFetcher petSwipesFetcher;

    String user1Id, user2Id, pet1Id, pet2Id, pet3Id;

    @BeforeEach
    public void setUp() {
        BCryptService bCryptService = new BCryptService();
        DummyUserRepository dummyUserRepository = new DummyUserRepository();
        dummyPetRepository = new DummyPetRepository(dummyUserRepository);

        UserCreator userCreator = new UserCreator(dummyUserRepository, bCryptService,
                new UserAccountValidator(), new DummyGeocoderService());
        PetCreator petCreator = new PetCreator(dummyPetRepository, dummyUserRepository, new PetProfileValidator());

        petSwipesFetcher = new PetSwipesFetcher(dummyPetRepository, new PetActionValidator(dummyPetRepository));


        // Create users
        user1Id = ((UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("John",
                "Appleseed", "20 St George Street", "Toronto",
                "Password123", "john.appleseed@email.com")).getResponseData()).getUserId();
        user2Id = ((UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("Mike",
                "Hunter", "21 St George Street", "Toronto", "Password123",
                "mike.the.hunter@email.com")).getResponseData()).getUserId();
        // Create pets
        pet1Id = ((PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(user1Id, user1Id, "Pocky", "5",
                "Golden Retriever", "The happiest dog in the world!")).getResponseData()).getPetId();
        pet2Id = ((PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(user2Id, user2Id, "Jack", "3",
                "German Shepherd", "Certified Good Boy TM.")).getResponseData()).getPetId();
        pet3Id = ((PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(user2Id, user2Id, "Bobert", "3",
                "Bulldog", "Me good dog.")).getResponseData()).getPetId();
    }

    /**
     * Test fetching a list of swiped pets for a valid user and pet
     */
    @Test()
    public void TestSuccessPetSwipesFetcher() {

        Boolean swipe1 = dummyPetRepository.swipePets(Integer.parseInt(pet1Id), Integer.parseInt(pet2Id));
        List<String> petIds1 = List.of(pet2Id);
        PetSwipesFetcherResponseModel expected1 = new PetSwipesFetcherResponseModel(petIds1);

        ResponseModel responseModel1 = petSwipesFetcher.fetchPetSwipes(
                new PetSwipesFetcherRequestModel(user1Id, pet1Id));

        PetSwipesFetcherResponseModel responseData1 = (PetSwipesFetcherResponseModel) responseModel1.getResponseData();

        assertTrue(responseModel1.isSuccess);
        assertEquals(expected1.getPetIds(), responseData1.getPetIds());

        Boolean swipe2 = dummyPetRepository.swipePets(Integer.parseInt(pet1Id), Integer.parseInt(pet3Id));
        List<String> petIds2 = List.of(pet2Id, pet3Id);
        PetSwipesFetcherResponseModel expected2 = new PetSwipesFetcherResponseModel(petIds2);

        ResponseModel responseModel2 = petSwipesFetcher.fetchPetSwipes(
                new PetSwipesFetcherRequestModel(user1Id, pet1Id));

        PetSwipesFetcherResponseModel responseData2 = (PetSwipesFetcherResponseModel) responseModel2.getResponseData();

        assertTrue(responseModel2.isSuccess);
        assertEquals(expected2.getPetIds(), responseData2.getPetIds());

    }

    /**
     * Test fetching a list of swiped pets for a valid userId and an invalid petId
     */
    @Test()
    public void TestFailPetSwipesFetcher() {
        ResponseModel responseModel = petSwipesFetcher.fetchPetSwipes(
                new PetSwipesFetcherRequestModel(user1Id, "4"));
        assertFalse(responseModel.isSuccess);
    }


}
