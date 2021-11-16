package server.use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.entities.Pet;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestPetUnswiper {

    private DummyUserRepository userRepository;
    private DummyPetRepository petRepository;
    private PetSwiper petSwiper;
    private PetUnswiper petUnswiper;

    @BeforeEach
    public void setUp() {
        userRepository = new DummyUserRepository();
        petRepository = new DummyPetRepository(userRepository);
        petSwiper = new PetSwiper(petRepository);
        petUnswiper = new PetUnswiper(petRepository);
    }

    @Test
    public void TestSuccessPetUnswiper() throws PetNotFoundException {
        int user1Id = userRepository.createUser("John", "Appleseed", "123 Addy", "Toronto", "123456", "john.appleseed@gmail.com");
        int user2Id = userRepository.createUser("Lig", "Mother", "42 Main St", "Toronto", "!password!11", "lig.ma@email.com");
        int pet1Id = petRepository.createPet(user1Id, "Nugget", 1, "Golden Shepherd", "A golden boy.");
        int pet2Id = petRepository.createPet(user2Id, "Jack", 2, "Husky", "Certified Good Boy.");

        ResponseModel responseA = petSwiper.swipe(new PetSwiperRequestModel(user1Id + "", pet1Id, pet2Id));
        ResponseModel responseB = petUnswiper.unswipePets(new PetUnswiperRequestModel(user1Id + "", pet1Id, pet2Id));

        // Check for the pet's swiped list
        List<Integer> expectedPet1SwipesList = new ArrayList<>();
        List<Integer> actual = petRepository.fetchSwipedOn(pet1Id);

        assertTrue(responseA.isSuccess() && responseB.isSuccess());
        assertEquals(expectedPet1SwipesList, actual);
    }

    @Test
    public void TestFailPetUnswiper() throws PetNotFoundException {
        int user1Id = userRepository.createUser("John", "Appleseed", "123 Addy", "Toronto", "123456", "john.appleseed@gmail.com");
        int user2Id = userRepository.createUser("Lig", "Mother", "42 Main St", "Toronto", "!password!11", "lig.ma@email.com");
        int pet1Id = petRepository.createPet(user1Id, "Nugget", 1, "Golden Shepherd", "A golden boy.");
        int pet2Id = petRepository.createPet(user2Id, "Jack", 2, "Husky", "Certified Good Boy.");

        ResponseModel response = petUnswiper.unswipePets(new PetUnswiperRequestModel(user1Id + "", pet1Id, pet2Id));

        // Check for the pet's swiped list
        List<Integer> expectedPet1SwipesList = new ArrayList<>();
        assertTrue(!response.isSuccess());
        assertEquals(petRepository.fetchSwipedOn(pet1Id), expectedPet1SwipesList);
    }

}
