package server.use_cases;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPetCreator {

    private static PetCreator petCreator;
    private static UserCreator userCreator;

    @BeforeAll
    public static void setUp() {
        DummyUserRepository userRepository = new DummyUserRepository();
        DummyPetRepository petRepository = new DummyPetRepository();

        userCreator = new UserCreator(userRepository);
        petCreator = new PetCreator(petRepository, userRepository);
    }

    @Test
    public void TestSuccessCreatePet() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = userCreator.createUser(new UserCreatorRequestModel("John", "Appleseed", "20 St George Street",
                "Toronto", "123456", "john.appleseed@gmail.com"));

        String userId = userCreatorResponse.getUserId();

        PetCreatorResponseModel expected = new PetCreatorResponseModel(true, "0", "0", "Koko", "3",
                "Dog", "Nice");

        PetCreatorResponseModel actual = petCreator.createPet(new PetCreatorRequestModel(userId, "Koko", 3,
                "Dog", "Nice"));

        assertEquals(expected, actual);
    }
}
