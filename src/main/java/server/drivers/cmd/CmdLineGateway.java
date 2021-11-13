package server.drivers.cmd;

import org.springframework.stereotype.Component;
import server.controllers.APIGateway;
import server.controllers.IPetController;
import server.controllers.IUserController;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.IPetRepository;

import java.util.List;

/**
 * A gateway that makes a connection between the command line (as input)
 * and the controllers in our program. This class is complementary to the CmdLineRunner class.
 */
@Component
public class CmdLineGateway implements APIGateway {

    private final IUserRepository userRepository;
    private final IUserController userController;
    private final IPetRepository petRepository;
    private final IPetController petController;

    public CmdLineGateway(IUserRepository userRepository, IUserController userController, IPetRepository petRepository,
                          IPetController petController) {
        this.userRepository = userRepository;
        this.userController = userController;
        this.petRepository = petRepository;
        this.petController = petController;
    }

    @Override
    public String createUser(String firstName, String lastName, String currentAddress, String currentCity, String password, String email) {
        String userJson = userController.createUser(firstName, lastName, currentAddress, currentCity, password, email);
        return userJson;
    }

    @Override
    public String fetchUserAccount(String userId) {
        return userController.fetchUserAccount(userId);
    }

    @Override
    public String createPet(int userId, String name, int age, String breed, String biography) {
        return petController.createPet(userId, name, age, breed, biography);
    }

    @Override
    public String fetchPetProfile(String petId) {
        return petController.fetchPetProfile(petId);
    }

    @Override
    public String editPet(String petId, String newName, int newAge, String newBreed, String newBiography) {
        return petController.editPet(petId, newName, newAge, newBreed, newBiography);
    }

    @Override
    public String swipePets(int pet1Id, int pet2Id) {
        return petController.swipePets(pet1Id, pet2Id);
    }

    @Override
    public String unswipePets(int pet1Id, int pet2Id) {
        return petController.unswipePets(pet1Id, pet2Id);
    }

    @Override
    public String rejectPets(int pet1Id, int pet2Id) {
        return petController.rejectPets(pet1Id, pet2Id);
    }

    @Override
    public String fetchPetSwipes(int petId) {
        return petController.fetchPetSwipes(petId);
    }

    @Override
    public String fetchPetMatches(int petId) {
        return petController.fetchPetMatches(petId);
    }

    @Override
    public String fetchUserPets(int userId) {
        return userController.fetchUserPets(userId);
    }

}
