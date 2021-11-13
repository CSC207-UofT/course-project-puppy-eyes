package server.drivers.cmd;

import org.springframework.stereotype.Component;
import server.controllers.APIGateway;
import server.controllers.IPetController;
import server.controllers.IUserController;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.IPetRepository;

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
    public String createUser(String firstName, String lastName, String currentAddress, String currentCity,
                             String password, String email) {
        String userJson = userController.createUser(firstName, lastName, currentAddress, currentCity, password, email);
        return userJson;
    }

    @Override
    public String fetchUserAccount(String userId) {
        return userController.fetchUserAccount(userId);
    }

    @Override
    public String editUserAccount(String userId, String newFirstName, String newLastName, String newAddress,
                                  String newCity, String newPassword, String newEmail) {
        return userController.editUserAccount(userId, newFirstName, newLastName, newAddress, newCity, newPassword,
                newEmail);
    }

    @Override
    public String fetchUserProfile(String userId) {
        return userController.fetchUserProfile(userId);
    }

    @Override
    public String editUserProfile(String userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook) {
        return userController.editUserProfile(userId, newBiography, newPhoneNumber, newInstagram, newFacebook);
    }

    @Override
    public String createPet(String userId, String name, int age, String breed, String biography) {
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

    // TODO define these methods in interface
    public String swipePets(int pet1Id, int pet2Id) {
        return petController.swipePets(pet1Id, pet2Id);
    }

    public String unswipePets(int pet1Id, int pet2Id) {
        return petController.unswipePets(pet1Id, pet2Id);
    }

    public String matchPets(int pet1Id, int pet2Id) {
        return petController.matchPets(pet1Id, pet2Id);
    }

}
