package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.entities.Pet;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.ResponseModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestPetSwiper {

    private DummyUserRepository userRepository;
    private DummyPetRepository petRepository;
    private PetSwiper petSwiper;

    @BeforeEach
    public void setUp() {
        DummyRelationRepository relationRepository = new DummyRelationRepository();
        userRepository = new DummyUserRepository();
        petRepository = new DummyPetRepository(userRepository, relationRepository);
        petSwiper = new PetSwiper(relationRepository, petRepository);
    }

    @Test
    public void TestSuccessPetSwiperNoMatch() {
        int user1Id = userRepository.createUser("John", "Appleseed", "123 Addy", "Toronto", "123456", "john.appleseed@gmail.com");
        int user2Id = userRepository.createUser("Lig", "Mother", "42 Main St", "Toronto", "!password!11", "lig.ma@email.com");
        int pet1Id = petRepository.createPet(user1Id, "Nugget", 1, "Golden Shepherd", "A golden boy.");
        int pet2Id = petRepository.createPet(user2Id, "Jack", 2, "Husky", "Certified Good Boy.");

        ResponseModel response = petSwiper.swipe(new PetSwiperRequestModel(user1Id + "", pet1Id, pet2Id));

        Pet pet1 = null;

        try {
            pet1 = petRepository.fetchPet(pet1Id);
        } catch (PetNotFoundException e) { }

        // Check for the pet's swiped list
        List<Integer> expected = Arrays.asList(1);
        List<Integer> actual = pet1.getSwipedOn();

        assertTrue(response.isSuccess());
        assertEquals(expected, actual);
    }

    @Test
    public void TestSuccessPetSwiperMatch() {
        int user1Id = userRepository.createUser("John", "Appleseed", "123 Addy", "Toronto", "123456", "john.appleseed@gmail.com");
        int user2Id = userRepository.createUser("Lig", "Mother", "42 Main St", "Toronto", "!password!11", "lig.ma@email.com");
        int pet1Id = petRepository.createPet(user1Id, "Nugget", 1, "Golden Shepherd", "A golden boy.");
        int pet2Id = petRepository.createPet(user2Id, "Jack", 2, "Husky", "Certified Good Boy.");

        ResponseModel responseA = petSwiper.swipe(new PetSwiperRequestModel(user1Id + "", pet1Id, pet2Id));
        ResponseModel responseB = petSwiper.swipe(new PetSwiperRequestModel(user2Id + "", pet2Id, pet1Id));

        Pet pet1 = null;
        Pet pet2 = null;

        try {
            pet1 = petRepository.fetchPet(pet1Id);
            pet2 = petRepository.fetchPet(pet2Id);
        } catch (PetNotFoundException e) { }

        // Check for the pet's swiped list
        List<Integer> expectedPetSwipesList = new ArrayList<>();
        List<Integer> expectedPet1MatchesList = pet1.getMatches();
        List<Integer> expectedPet2MatchesList = pet1.getMatches();

        assertTrue(responseA.isSuccess() && responseB.isSuccess());
        assertEquals(pet1.getSwipedOn(), expectedPetSwipesList);
        assertEquals(pet2.getSwipedOn(), expectedPetSwipesList);
        assertEquals(pet1.getMatches(), expectedPet1MatchesList);
        assertEquals(pet2.getMatches(), expectedPet2MatchesList);
    }

    @Test
    public void TestFailPetSwiperAlreadySwiped() {
        int user1Id = userRepository.createUser("John", "Appleseed", "123 Addy", "Toronto", "123456", "john.appleseed@gmail.com");
        int user2Id = userRepository.createUser("Lig", "Mother", "42 Main St", "Toronto", "!password!11", "lig.ma@email.com");
        int pet1Id = petRepository.createPet(user1Id, "Nugget", 1, "Golden Shepherd", "A golden boy.");
        int pet2Id = petRepository.createPet(user2Id, "Jack", 2, "Husky", "Certified Good Boy.");

        ResponseModel responseA = petSwiper.swipe(new PetSwiperRequestModel(user1Id + "", pet1Id, pet2Id));
        ResponseModel responseB = petSwiper.swipe(new PetSwiperRequestModel(user1Id + "", pet1Id, pet2Id));

        Pet pet1 = null;

        try {
            pet1 = petRepository.fetchPet(pet1Id);
        } catch (PetNotFoundException e) { }

        // Check for the pet's swiped list
        List<Integer> expectedPet1SwipesList = Arrays.asList(1);
        assertTrue(responseA.isSuccess() && !responseB.isSuccess());
        assertEquals(pet1.getSwipedOn(), expectedPet1SwipesList);
    }

}
