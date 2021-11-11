package server.use_cases;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPetCreator {
    private static PetCreator petCreator;

    @BeforeAll
    public static void setUp() {
        petCreator = new PetCreator(new DummyPetRepository());
    }

    @Test
    public void TestSuccessCreatePet() {
        PetCreatorResponseModel expected = new PetCreatorResponseModel(true, "Koko", 3,
                "Dog", "Nice", "0");

        PetCreatorResponseModel actual = petCreator.createPet(new PetCreatorRequestModel("Koko", 3,
                "Dog", "Nice"));

        assertEquals(expected, actual);
    }
}
