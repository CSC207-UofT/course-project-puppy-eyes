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

    DummyRelationRepository relationRepository;
    private DummyUserRepository userRepository;
    private DummyPetRepository petRepository;
    private PetSwiper petSwiper;
    private PetUnswiper petUnswiper;

    @BeforeEach
    public void setUp() {
        relationRepository = new DummyRelationRepository();
        userRepository = new DummyUserRepository();
        petRepository = new DummyPetRepository(userRepository, relationRepository);
        petSwiper = new PetSwiper(relationRepository, petRepository);
        petUnswiper = new PetUnswiper(relationRepository, petRepository);
    }

    @Test
    public void TestSuccessPetUnswiper() {
        int user1Id = userRepository.createUser("John", "Appleseed", "123 Addy", "Toronto", "123456", "john.appleseed@gmail.com");
        int user2Id = userRepository.createUser("Lig", "Mother", "42 Main St", "Toronto", "!password!11", "lig.ma@email.com");
        int pet1Id = petRepository.createPet(user1Id, "Nugget", 1, "Golden Shepherd", "A golden boy.");
        int pet2Id = petRepository.createPet(user2Id, "Jack", 2, "Husky", "Certified Good Boy.");

        ResponseModel responseA = petSwiper.swipe(new PetSwiperRequestModel(user1Id + "", pet1Id, pet2Id));
        ResponseModel responseB = petUnswiper.unswipePets(new PetUnswiperRequestModel(user1Id + "", pet1Id, pet2Id));

        Pet pet1 = null;

        try {
            pet1 = petRepository.fetchPet(pet1Id);
        } catch (PetNotFoundException e) { }

        // Check for the pet's swiped list
        List<Integer> expectedPet1SwipesList = new ArrayList<>();
        List<Integer> actual = pet1.getSwipedOn();

        assertTrue(responseA.isSuccess() && responseB.isSuccess());
        assertEquals(expectedPet1SwipesList, actual);
    }

    @Test
    public void TestFailPetUnswiper() {
        int user1Id = userRepository.createUser("John", "Appleseed", "123 Addy", "Toronto", "123456", "john.appleseed@gmail.com");
        int user2Id = userRepository.createUser("Lig", "Mother", "42 Main St", "Toronto", "!password!11", "lig.ma@email.com");
        int pet1Id = petRepository.createPet(user1Id, "Nugget", 1, "Golden Shepherd", "A golden boy.");
        int pet2Id = petRepository.createPet(user2Id, "Jack", 2, "Husky", "Certified Good Boy.");

        ResponseModel response = petUnswiper.unswipePets(new PetUnswiperRequestModel(user1Id + "", pet1Id, pet2Id));

        Pet pet1 = null;

        try {
            pet1 = petRepository.fetchPet(pet1Id);
        } catch (PetNotFoundException e) { }

        // Check for the pet's swiped list
        List<Integer> expectedPet1SwipesList = new ArrayList<>();
        assertTrue(!response.isSuccess());
        assertEquals(pet1.getSwipedOn(), expectedPet1SwipesList);
    }

}
