package server.use_cases.pet_use_cases.pet_matches_fetcher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.drivers.BCryptService;
import server.drivers.IGeocoderService;
import server.use_cases.DummyGeocoderService;
import server.use_cases.DummyPetRepository;
import server.use_cases.DummyUserRepository;
import server.use_cases.ResponseModel;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidator;
import server.use_cases.pet_use_cases.pet_creator.PetCreator;
import server.use_cases.pet_use_cases.pet_creator.PetCreatorRequestModel;
import server.use_cases.pet_use_cases.pet_creator.PetCreatorResponseModel;
import server.use_cases.pet_use_cases.pet_matches_generator.PetMatchesGenerator;
import server.use_cases.pet_use_cases.pet_profile_validator.PetProfileValidator;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidator;
import server.use_cases.user_use_cases.user_creator.UserCreator;
import server.use_cases.user_use_cases.user_creator.UserCreatorRequestModel;
import server.use_cases.user_use_cases.user_creator.UserCreatorResponseModel;
import server.use_cases.user_use_cases.user_pets_fetcher.UserPetsFetcher;
import server.use_cases.user_use_cases.user_pets_fetcher.UserPetsFetcherResponseModel;

public class TestPetMatchesFetcher {

    private PetMatchesFetcher petMatchesFetcher;

    String user1Id, user2Id, pet2Id, pet1Id, pet3Id;

    @BeforeEach
    public void setUp() {
        IGeocoderService geocoderService = new DummyGeocoderService();
        BCryptService bcryptService = new BCryptService();
        DummyUserRepository userRepository = new DummyUserRepository();
        UserCreator userCreator = new UserCreator(userRepository, bcryptService, new UserAccountValidator(), geocoderService);
        DummyPetRepository petRepository = new DummyPetRepository(userRepository);
        PetCreator petCreator = new PetCreator(petRepository, userRepository, new PetProfileValidator());

        petMatchesFetcher = new PetMatchesFetcher(petRepository, new PetActionValidator(petRepository));

        // Create users
        user1Id = ((UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("John",
                "Appleseed", "20 St George Street", "Toronto",
                "Password123", "john.appleseed@email.com")).getResponseData()).getUserId();
        user2Id = ((UserCreatorResponseModel) userCreator.createUser(new UserCreatorRequestModel("Mike",
                "Hunter", "21 St George Street", "Toronto", "Password123",
                "mike.the.hunter@email.com")).getResponseData()).getUserId();
        // Create pets
        pet1Id = ((PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(user1Id, user1Id, "Pocky", "5",
                "Golden Retriever", "The happiest dog in the world!")).getResponseData()).getPetId();
        pet2Id = ((PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(user2Id, user2Id, "Jack", "3",
                "German Shepherd", "Certified Good Boy TM.")).getResponseData()).getPetId();
        pet3Id = ((PetCreatorResponseModel) petCreator.createPet(new PetCreatorRequestModel(user2Id, user2Id, "Mark", "3",
                "German Shepherd", "Certified Good Boy TM.")).getResponseData()).getPetId();
        petRepository.matchPets(Integer.parseInt(pet1Id), Integer.parseInt(pet2Id));
        petRepository.rejectPets(Integer.parseInt(pet1Id), Integer.parseInt(pet3Id));
    }

    @Test()
    public void TestSuccessfulPetMatchesFetcher() {
        ResponseModel responseModel = petMatchesFetcher.fetchPetMatches(
                new PetMatchesFetcherRequestModel(user1Id, pet1Id)
        );
        List<String> expected = Arrays.asList("1");

        List<String> actual = ((PetMatchesFetcherResponseModel) responseModel.getResponseData()).getPetIds();
        assert expected.equals(actual);

    }

    @Test()
    public void TestFailurePetMatchesFetcher() {
        ResponseModel responseModel = petMatchesFetcher.fetchPetMatches(
                new PetMatchesFetcherRequestModel(user1Id, pet1Id)
        );
        List<String> expected = Arrays.asList("1,2");


        List<String> actual = ((PetMatchesFetcherResponseModel) responseModel.getResponseData()).getPetIds();
        assert !expected.equals(actual);

    }
}
