package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidator;
import server.use_cases.pet_use_cases.pet_interactor.PetInteractionType;
import server.use_cases.pet_use_cases.pet_interactor.PetInteractor;
import server.use_cases.pet_use_cases.pet_interactor.PetInteractorRequestModel;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_use_cases.user_creator.UserCreator;
import server.use_cases.user_use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_use_cases.user_creator.UserCreatorResponseModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestPetSwiper {

    private DummyUserRepository userRepository;
    private DummyPetRepository petRepository;
    private PetInteractor petInteractor;

    int user1Id, user2Id, pet1Id, pet2Id;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        userRepository = new DummyUserRepository();
        petRepository = new DummyPetRepository(userRepository);
        petInteractor = new PetInteractor(petRepository, new PetActionValidator(petRepository));
        UserCreator userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator(), new DummyGeocoderService());

        // Create some users and pets
        user1Id = Integer.parseInt(((UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "John", "Appleseed", "123 Addy", "Toronto",
                        "Password123", "john.appleseed@gmail.com"
                )
        ).getResponseData()).getUserId());

        user2Id = Integer.parseInt(((UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "Lig", "Mother", "42 Main St", "Toronto",
                        "Password123", "lig.ma@email.com"
                )
        ).getResponseData()).getUserId());

        pet1Id = petRepository.createPet(user1Id, "Nugget", 1, "Golden Shepherd", "A golden boy.");
        pet2Id = petRepository.createPet(user2Id, "Jack", 2, "Husky", "Certified Good Boy.");
    }

    @Test
    public void TestSuccessPetSwiperNoMatch() {
        ResponseModel response = petInteractor.interact(new PetInteractorRequestModel(
                user1Id + "",
                pet1Id + "",
                pet2Id + "",
                PetInteractionType.SWIPE
        ));

        // Check for the pet's swiped list
        List<Integer> expected = Arrays.asList(1);
        List<Integer> actual = petRepository.fetchSwipedOn(pet1Id);

        assertTrue(response.isSuccess());
        assertEquals(expected, actual);
    }

    @Test
    public void TestSuccessPetSwiperMatch() {
        ResponseModel responseA = petInteractor.interact(new PetInteractorRequestModel(
                user1Id + "",
                pet1Id + "",
                pet2Id + "",
                PetInteractionType.SWIPE
        ));
        ResponseModel responseB = petInteractor.interact(new PetInteractorRequestModel(
                user2Id + "",
                pet2Id + "",
                pet1Id + "",
                PetInteractionType.SWIPE
        ));

        // Check for the pet's swiped list
        List<Integer> expectedPetSwipesList = new ArrayList<>();
        List<Integer> expectedPet1MatchesList = Arrays.asList(1);
        List<Integer> expectedPet2MatchesList = Arrays.asList(0);

        List<Integer> actualPet1SwipesList = petRepository.fetchSwipedOn(pet1Id);
        List<Integer> actualPet2SwipesList = petRepository.fetchSwipedOn(pet2Id);

        List<Integer> actualPet1MatchesList = petRepository.fetchMatches(pet1Id);
        List<Integer> actualPet2MatchesList = petRepository.fetchMatches(pet2Id);

        assertTrue(responseA.isSuccess() && responseB.isSuccess());
        assertEquals(expectedPetSwipesList, actualPet1SwipesList);
        assertEquals(expectedPetSwipesList, actualPet2SwipesList);
        assertEquals(expectedPet1MatchesList, actualPet1MatchesList);
        assertEquals(expectedPet2MatchesList, actualPet2MatchesList);
    }

    @Test
    public void TestFailPetSwiperAlreadySwiped() {
        ResponseModel responseA = petInteractor.interact(new PetInteractorRequestModel(
                user1Id + "",
                pet1Id + "",
                pet2Id + "",
                PetInteractionType.SWIPE
        ));
        ResponseModel responseB = petInteractor.interact(new PetInteractorRequestModel(
                user1Id + "",
                pet1Id + "",
                pet2Id + "",
                PetInteractionType.SWIPE
        ));

        // Check for the pet's swiped list
        List<Integer> expectedPet1SwipesList = Arrays.asList(1);

        List<Integer> actualPet1SwipesList = petRepository.fetchSwipedOn(pet1Id);

        assertTrue(responseA.isSuccess() && !responseB.isSuccess());
        assertEquals(expectedPet1SwipesList, actualPet1SwipesList);
    }

}
