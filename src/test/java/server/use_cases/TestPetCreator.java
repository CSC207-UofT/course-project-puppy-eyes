package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestPetCreator {

    private PetCreator petCreator;
    private UserCreator userCreator;

    @BeforeEach
    public void setUp() {
        DummyUserRepository userRepository = new DummyUserRepository();
        DummyRelationRepository relationRepository = new DummyRelationRepository();
        DummyPetRepository petRepository = new DummyPetRepository(userRepository, relationRepository);

        userCreator = new UserCreator(userRepository);
        petCreator = new PetCreator(petRepository, userRepository);
    }

    @Test
    public void TestSuccessCreatePet() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = (UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("John", "Appleseed", "20 St George Street",
                "Toronto", "123456", "john.appleseed@gmail.com")).getResponseData();

        String userId = userCreatorResponse.getUserId();

        PetCreatorResponseModel expected = new PetCreatorResponseModel("0", userId, "Koko", "3",
                "Dog", "Nice");

        PetCreatorResponseModel actual = (PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(userId, userId, "Koko", 3,
                "Dog", "Nice")).getResponseData();

        assertEquals(expected, actual);
    }
}
