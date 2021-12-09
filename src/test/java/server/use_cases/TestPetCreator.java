package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.use_cases.pet_use_cases.pet_creator.PetCreator;
import server.use_cases.pet_use_cases.pet_creator.PetCreatorRequestModel;
import server.use_cases.pet_use_cases.pet_creator.PetCreatorResponseModel;
import server.use_cases.pet_use_cases.pet_profile_validator.PetProfileValidator;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_use_cases.user_creator.UserCreator;
import server.use_cases.user_use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_use_cases.user_creator.UserCreatorResponseModel;

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

        userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator(), new DummyGeocoderService());
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

    @Test
    public void TestMissFieldsCreatePet() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = (UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "John", "Appleseed", "20 St George Street",
                        "Toronto", "Password123", "john.appleseed@gmail.com"
                )
        ).getResponseData();

        String userId = userCreatorResponse.getUserId();

        ResponseModel expected = new ResponseModel(false, "Missing required fields.");
        ;

        ResponseModel actual = petCreator.createPet(new PetCreatorRequestModel(null, null, null, null,
                null, "Nice"));

        assertEquals(expected, actual);
    }

    @Test
    public void TestValidIDCreatePet() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = (UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "John", "Appleseed", "20 St George Street",
                        "Toronto", "Password123", "john.appleseed@gmail.com"
                )
        ).getResponseData();

        String userId = userCreatorResponse.getUserId();

        ResponseModel expected = new ResponseModel(false, "ID must be an integer.");

        ResponseModel actual = petCreator.createPet(new PetCreatorRequestModel("null", "null", "Koko", "3",
                "Dog", "Nice"));

        assertEquals(expected, actual);
    }

    @Test
    public void TestValidAgeCreatePet() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = (UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "John", "Appleseed", "20 St George Street",
                        "Toronto", "Password123", "john.appleseed@gmail.com"
                )
        ).getResponseData();

        String userId = userCreatorResponse.getUserId();

        ResponseModel expected = new ResponseModel(false, "Age must be an integer.");

        ResponseModel actual = petCreator.createPet(new PetCreatorRequestModel(userId, userId, "Koko", "wrong age",
                "Dog", "Nice"));

        assertEquals(expected, actual);
    }

    @Test
    public void TestValidUserCreatePet() {
        // Create some users
        UserCreatorResponseModel userCreatorResponse = (UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "John", "Appleseed", "20 St George Street",
                        "Toronto", "Password123", "john.appleseed@gmail.com"
                )
        ).getResponseData();

        String userId = userCreatorResponse.getUserId();

        ResponseModel expected = new ResponseModel(false, "User with ID: " + "10000000" + " does not exist.");

        ResponseModel actual = petCreator.createPet(new PetCreatorRequestModel("10000000", "10000000", "Koko", "3",
                "Dog", "Nice"));

        assertEquals(expected, actual);
    }
}
