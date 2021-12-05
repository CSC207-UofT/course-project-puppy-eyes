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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestPetUnswiper {

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
    public void TestSuccessPetUnswiper() {
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
                PetInteractionType.UNSWIPE
        ));

        // Check for the pet's swiped list
        List<Integer> expectedPet1SwipesList = new ArrayList<>();
        List<Integer> actual = petRepository.fetchSwipedOn(pet1Id);

        assertTrue(responseA.isSuccess() && responseB.isSuccess());
        assertEquals(expectedPet1SwipesList, actual);
    }

    @Test
    public void TestFailPetUnswiper() {
        ResponseModel response = petInteractor.interact(new PetInteractorRequestModel(
                user1Id + "",
                pet1Id + "",
                pet2Id + "",
                PetInteractionType.UNSWIPE
        ));

        // Check for the pet's swiped list
        List<Integer> expectedPet1SwipesList = new ArrayList<>();
        assertTrue(!response.isSuccess());
        assertEquals(petRepository.fetchSwipedOn(pet1Id), expectedPet1SwipesList);
    }

}
