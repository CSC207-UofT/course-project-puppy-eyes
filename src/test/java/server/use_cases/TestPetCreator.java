package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestPetCreator {

    private PetCreator petCreator;
    private UserCreator userCreator;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        DummyUserRepository userRepository = new DummyUserRepository();
        DummyPetRepository petRepository = new DummyPetRepository(userRepository);

        userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator());
        petCreator = new PetCreator(petRepository, userRepository, new PetProfileValidator());
    }

    @Test
    public void TestSuccessCreatePet() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = (UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                    "John", "Appleseed", "20 St George Street",
                    "Toronto", "Password123", "john.appleseed@gmail.com"
                )
        ).getResponseData();

        String userId = userCreatorResponse.getUserId();

        PetCreatorResponseModel expected = new PetCreatorResponseModel("0", userId, "Koko", "3",
                "Dog", "Nice");

        PetCreatorResponseModel actual = (PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(userId, userId, "Koko", "3",
                "Dog", "Nice")).getResponseData();

        assertEquals(expected, actual);
    }
}
