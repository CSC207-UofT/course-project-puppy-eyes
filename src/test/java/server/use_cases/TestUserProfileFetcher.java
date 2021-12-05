package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.drivers.BCryptService;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidator;
import server.use_cases.pet_use_cases.pet_creator.PetCreator;
import server.use_cases.pet_use_cases.pet_creator.PetCreatorRequestModel;
import server.use_cases.pet_use_cases.pet_creator.PetCreatorResponseModel;
import server.use_cases.pet_use_cases.pet_interactor.PetInteractionType;
import server.use_cases.pet_use_cases.pet_interactor.PetInteractor;
import server.use_cases.pet_use_cases.pet_interactor.PetInteractorRequestModel;
import server.use_cases.pet_use_cases.pet_profile_validator.PetProfileValidator;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidator;
import server.use_cases.user_use_cases.user_creator.UserCreator;
import server.use_cases.user_use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_use_cases.user_creator.UserCreatorResponseModel;
import server.use_cases.user_use_cases.user_profile_fetcher.UserProfileFetcher;
import server.use_cases.user_use_cases.user_profile_fetcher.UserProfileFetcherRequestModel;
import server.use_cases.user_use_cases.user_profile_fetcher.UserProfileFetcherResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestUserProfileFetcher {

    private UserProfileFetcher userProfileFetcher;

    private int user1Id, user2Id, user3Id, pet1Id, pet2Id;

    @BeforeEach
    public void setUp() {
        BCryptService bcryptService = new BCryptService();
        DummyUserRepository userRepository = new DummyUserRepository();
        DummyPetRepository petRepository = new DummyPetRepository(userRepository);
        userProfileFetcher = new UserProfileFetcher(userRepository, petRepository, new UserActionValidator(userRepository));

        UserCreator userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator(), new DummyGeocoderService());
        PetCreator petCreator = new PetCreator(petRepository, userRepository, new PetProfileValidator());
        PetInteractor petInteractor = new PetInteractor(petRepository, new PetActionValidator(petRepository));

        // Fill the repository with some dummy users
        user1Id = Integer.parseInt(((UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "John", "Appleseed", "123 Addy", "Toronto",
                        "Password123", "john.appleseed@gmail.com"
                )
        ).getResponseData()).getUserId());

        user2Id = Integer.parseInt(((UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "Michael", "Hunt", "29 St George Street", "Toronto",
                        "Password123", "mikehunt@gmail.com"
                )
        ).getResponseData()).getUserId());

        user3Id = Integer.parseInt(((UserCreatorResponseModel) userCreator.createUser(
                new UserCreatorRequestModel(
                        "John", "Wick", "60 St George Street", "Toronto",
                        "Password123", "johnwick@gmail.com"
                )
        ).getResponseData()).getUserId());

        userRepository.editUserProfile(user1Id, "John's Biography", "1234567890", "therealjohnappleseed", "johnappleseedofficial");
        userRepository.editUserProfile(user2Id, "Mike's Biography", "6479991234", "mikethehunter", "michael.hunt99");

        // Create some pets
        pet1Id = Integer.parseInt(((PetCreatorResponseModel) petCreator.createPet(
                new PetCreatorRequestModel(
                        user1Id + "", user1Id + "", "Casper", "1", "Ghost", "Boo!"
                )
        ).getResponseData()).getPetId());

        pet2Id = Integer.parseInt(((PetCreatorResponseModel) petCreator.createPet(
                new PetCreatorRequestModel(
                        user2Id + "", user2Id + "", "Jake", "12", "German Shepherd", "Woof!"
                )
        ).getResponseData()).getPetId());

        // Match pet1 and pet2
        petInteractor.interact(new PetInteractorRequestModel(user1Id + "", pet1Id + "", pet2Id + "", PetInteractionType.SWIPE));
        petInteractor.interact(new PetInteractorRequestModel(user2Id + "", pet2Id + "", pet1Id + "", PetInteractionType.SWIPE));
    }

    /**
     * Test fetching a user's profile with a valid id.
     */
    @Test
    public void TestFetchUserProfileWithValidId() {
        UserProfileFetcherResponseModel expected = new UserProfileFetcherResponseModel("John", "Appleseed", "John's Biography", "1234567890", "john.appleseed@gmail.com", "therealjohnappleseed", "johnappleseedofficial");
        ResponseModel responseModel = userProfileFetcher.fetchUserProfile(new UserProfileFetcherRequestModel(user1Id + "", user1Id + ""));

        UserProfileFetcherResponseModel actual = (UserProfileFetcherResponseModel) responseModel.getResponseData();

        assertTrue(responseModel.isSuccess());
        assertEquals(expected, actual);
    }

    /**
     * Test fetching a user's profile from another authorized user
     */
    @Test
    public void TestFetchUserProfileFromOtherAuthorized() {
        UserProfileFetcherResponseModel expected = new UserProfileFetcherResponseModel("John", "Appleseed", "John's Biography", "1234567890", "john.appleseed@gmail.com", "therealjohnappleseed", "johnappleseedofficial");
        ResponseModel responseModel = userProfileFetcher.fetchUserProfile(new UserProfileFetcherRequestModel(user2Id + "", user1Id + ""));

        UserProfileFetcherResponseModel actual = (UserProfileFetcherResponseModel) responseModel.getResponseData();

        assertTrue(responseModel.isSuccess());
        assertEquals(expected, actual);
    }

    /**
     * Test fetching a user's profile from another unauthorized user
     */
    @Test
    public void TestFetchUserProfileFromOtherUnauthorized() {
        ResponseModel expected = new ResponseModel();
        ResponseModel actual = userProfileFetcher.fetchUserProfile(new UserProfileFetcherRequestModel(user3Id + "", user1Id + ""));

        assertTrue(!actual.isSuccess());
        assertEquals(expected, actual);
    }

    /**
     * Test fetching a user's profile with an invalid id.
     */
    @Test
    public void TestFetchUserProfileWithoutValidId() {
        ResponseModel expected = new ResponseModel(false, "User with ID: 42 does not exist.");
        ResponseModel actual = userProfileFetcher.fetchUserProfile(new UserProfileFetcherRequestModel("42", "42"));

        assertEquals(expected, actual);
    }
}
