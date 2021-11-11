package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPetEditor {
    private static DummyPetRepository dummyPetRepository;
    private static PetEditor petEditor;

    @BeforeEach
    public void setUp() {
        dummyPetRepository = new DummyPetRepository();
        petEditor = new PetEditor(dummyPetRepository);
    }

    @Test
    public void TestEditPetWithValidId() {
        dummyPetRepository.createPet("Amy", 100, "Turtle", "Ahhhh");
        dummyPetRepository.createPet("Bob", 2, "Dog", "Bobobobobo");
        dummyPetRepository.createPet("Cindy", 7, "Cat", "Meow");

        PetEditorResponseModel expected = new PetEditorResponseModel(true, "Koko", 5, "Bird", "Hello", "2");
        PetEditorResponseModel actual = petEditor.editPet(new PetEditorRequestModel("2", "Koko", 5, "Bird", "Hello"));

        assertEquals(expected, actual);
    }

    @Test void TestEditPetWithouValidId() {
        dummyPetRepository.createPet("Amy", 100, "Turtle", "Ahhhh");
        dummyPetRepository.createPet("Bob", 2, "Dog", "Bobobobobo");
        dummyPetRepository.createPet("Cindy", 7, "Cat", "Meow");

        PetEditorResponseModel expected = new PetEditorResponseModel(false, "Koko", 5, "Bird", "Hello", "3");
        PetEditorResponseModel actual = petEditor.editPet(new PetEditorRequestModel("3", "Koko", 5, "Bird", "Hello"));

        assertEquals(expected, actual);
    }
}
