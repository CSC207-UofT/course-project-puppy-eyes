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
        userRepository = new DummyUserRepository();
        petRepository = new DummyPetRepository(userRepository);
        petSwiper = new PetSwiper(petRepository);
    }

    @Test
    public void TestSuccessPetSwiperNoMatch() throws PetNotFoundException {
        int user1Id = userRepository.createUser("John", "Appleseed", "123 Addy", "Toronto", "123456", "john.appleseed@gmail.com");
        int user2Id = userRepository.createUser("Lig", "Mother", "42 Main St", "Toronto", "!password!11", "lig.ma@email.com");
        int pet1Id = petRepository.createPet(user1Id, "Nugget", 1, "Golden Shepherd", "A golden boy.");
        int pet2Id = petRepository.createPet(user2Id, "Jack", 2, "Husky", "Certified Good Boy.");

        ResponseModel response = petSwiper.swipe(new PetSwiperRequestModel(user1Id + "", pet1Id, pet2Id));

        // Check for the pet's swiped list
        List<Integer> expected = Arrays.asList(1);
        List<Integer> actual = petRepository.fetchSwipedOn(pet1Id);

        assertTrue(response.isSuccess());
        assertEquals(expected, actual);
    }

    @Test
    public void TestSuccessPetSwiperMatch() throws PetNotFoundException {
        int user1Id = userRepository.createUser("John", "Appleseed", "123 Addy", "Toronto", "123456", "john.appleseed@gmail.com");
        int user2Id = userRepository.createUser("Lig", "Mother", "42 Main St", "Toronto", "!password!11", "lig.ma@email.com");
        int pet1Id = petRepository.createPet(user1Id, "Nugget", 1, "Golden Shepherd", "A golden boy.");
        int pet2Id = petRepository.createPet(user2Id, "Jack", 2, "Husky", "Certified Good Boy.");

        ResponseModel responseA = petSwiper.swipe(new PetSwiperRequestModel(user1Id + "", pet1Id, pet2Id));
        ResponseModel responseB = petSwiper.swipe(new PetSwiperRequestModel(user2Id + "", pet2Id, pet1Id));

        // Check for the pet's swiped list
        List<Integer> expectedPetSwipesList = new ArrayList<>();
        List<Integer> expectedPet1MatchesList = Arrays.asList(1);
        List<Integer> expectedPet2MatchesList = Arrays.asList(0);

        List<Integer> actualPet1SwipesList = petRepository.fetchSwipedOn(pet1Id);
        List<Integer> actualPet2SwipesList = petRepository.fetchSwipedOn(pet2Id);

        List<Integer> actualPet1MatchesList = petRepository.fetchMatches(pet1Id);
        List<Integer> actualPet2MatchesList = petRepository.fetchMatches(pet2Id);

        assertTrue(responseA.isSuccess() && responseB.isSuccess());
        assertEquals(expectedPetSwipesList, actualPet1SwipesList);
        assertEquals(expectedPetSwipesList, actualPet2SwipesList);
        assertEquals(expectedPet1MatchesList, actualPet1MatchesList);
        assertEquals(expectedPet2MatchesList, actualPet2MatchesList);
    }

    @Test
    public void TestFailPetSwiperAlreadySwiped() throws PetNotFoundException {
        int user1Id = userRepository.createUser("John", "Appleseed", "123 Addy", "Toronto", "123456", "john.appleseed@gmail.com");
        int user2Id = userRepository.createUser("Lig", "Mother", "42 Main St", "Toronto", "!password!11", "lig.ma@email.com");
        int pet1Id = petRepository.createPet(user1Id, "Nugget", 1, "Golden Shepherd", "A golden boy.");
        int pet2Id = petRepository.createPet(user2Id, "Jack", 2, "Husky", "Certified Good Boy.");

        ResponseModel responseA = petSwiper.swipe(new PetSwiperRequestModel(user1Id + "", pet1Id, pet2Id));
        ResponseModel responseB = petSwiper.swipe(new PetSwiperRequestModel(user1Id + "", pet1Id, pet2Id));

        // Check for the pet's swiped list
        List<Integer> expectedPet1SwipesList = Arrays.asList(1);

        List<Integer> actualPet1SwipesList = petRepository.fetchSwipedOn(pet1Id);

        assertTrue(responseA.isSuccess() && !responseB.isSuccess());
        assertEquals(expectedPet1SwipesList, actualPet1SwipesList);
    }

}
