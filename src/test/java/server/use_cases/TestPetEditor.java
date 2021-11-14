package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPetEditor {
    private static DummyPetRepository dummyPetRepository;
    private static PetEditor petEditor;
    private static UserCreator userCreator;

    @BeforeEach
    public void setUp() {
        DummyUserRepository userRepository = new DummyUserRepository();
        dummyPetRepository = new DummyPetRepository();

        userCreator = new UserCreator(userRepository);
        petEditor = new PetEditor(dummyPetRepository);
    }

    @Test
    public void TestEditPetWithValidId() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = (UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("John", "Appleseed", "20 St George Street",
                "Toronto", "123456", "john.appleseed@gmail.com")).getResponseData();

        int userId = Integer.parseInt(userCreatorResponse.getUserId());

        dummyPetRepository.createPet(userId,"Amy", 100, "Turtle", "Ahhhh");
        dummyPetRepository.createPet(userId,"Bob", 2, "Dog", "Bobobobobo");
        dummyPetRepository.createPet(userId,"Cindy", 7, "Cat", "Meow");

        PetEditorResponseModel expected = new PetEditorResponseModel("Koko", 5,
                "Bird", "Hello", "2");
        PetEditorResponseModel actual = (PetEditorResponseModel) petEditor.editPet(new PetEditorRequestModel(String.valueOf(userId),"2", "Koko",
                5, "Bird", "Hello")).getResponseData();

        assertEquals(expected, actual);
    }

    @Test void TestEditPetWithoutValidId() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = userCreator.createUser(new UserCreatorRequestModel("John", "Appleseed", "20 St George Street",
                "Toronto", "123456", "john.appleseed@gmail.com"));

        int userId = Integer.parseInt(userCreatorResponse.getUserId());

        dummyPetRepository.createPet(userId,"Amy", 100, "Turtle", "Ahhhh");
        dummyPetRepository.createPet(userId,"Bob", 2, "Dog", "Bobobobobo");
        dummyPetRepository.createPet(userId,"Cindy", 7, "Cat", "Meow");

        PetEditorResponseModel expected = new PetEditorResponseModel(false, "Koko", 5,
                "Bird", "Hello", "3");
        PetEditorResponseModel actual = petEditor.editPet(new PetEditorRequestModel("3", "Koko",
                5, "Bird", "Hello"));

        assertEquals(expected, actual);
    }
}
