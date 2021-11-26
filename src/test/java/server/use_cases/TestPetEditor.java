package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidator;
import server.use_cases.pet_use_cases.pet_editor.PetEditor;
import server.use_cases.pet_use_cases.pet_editor.PetEditorRequestModel;
import server.use_cases.pet_use_cases.pet_editor.PetEditorResponseModel;
import server.use_cases.pet_use_cases.pet_profile_validator.PetProfileValidator;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_use_cases.user_creator.UserCreator;
import server.use_cases.user_use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_use_cases.user_creator.UserCreatorResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestPetEditor {
    private DummyPetRepository dummyPetRepository;
    private PetEditor petEditor;
    private UserCreator userCreator;

    private int userId;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        DummyUserRepository userRepository = new DummyUserRepository();
        dummyPetRepository = new DummyPetRepository(userRepository);

        userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator());
        petEditor = new PetEditor(dummyPetRepository, new PetProfileValidator(), new PetActionValidator(dummyPetRepository));

        // Create some users
        UserCreatorResponseModel userCreatorResponse = (UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "John", "Appleseed", "20 St George Street",
                        "Toronto", "Password123", "john.appleseed@gmail.com"
                )
        ).getResponseData();

        userId = Integer.parseInt(userCreatorResponse.getUserId());

        // Create some pets
        dummyPetRepository.createPet(userId,"Amy", 100, "Turtle", "Ahhhh");
        dummyPetRepository.createPet(userId,"Bob", 2, "Dog", "Bobobobobo");
        dummyPetRepository.createPet(userId,"Cindy", 7, "Cat", "Meow");
    }

    @Test
    public void TestEditPetWithValidId() {
        PetEditorResponseModel expected = new PetEditorResponseModel("Koko", 5,
                "Bird", "Hello", "2");
        PetEditorResponseModel actual = (PetEditorResponseModel) petEditor.editPet(new PetEditorRequestModel(String.valueOf(userId),"2", "Koko",
                "5", "Bird", "Hello")).getResponseData();

        assertEquals(expected, actual);
    }

    @Test void TestEditPetWithoutValidId() {
        ResponseModel expected = new ResponseModel(false, "Pet with ID: 3 does not exist.");
        ResponseModel actual = petEditor.editPet(new PetEditorRequestModel(String.valueOf(userId),
                "3", "Koko", "5", "Bird", "Hello"));

        assertEquals(expected, actual);
        assertTrue(!actual.isSuccess());
    }
}
