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

    public CmdLineGateway(IUserRepository userRepository, IUserController userController, IPetRepository petRepository, IPetController petController) {
        this.userRepository = userRepository;
        this.userController = userController;
        this.petRepository = petRepository;
        this.petController = petController;
    }

    @Override
    public String createUser(String firstName, String lastName, String homeAddress, String password, String email) {
        String userJson = userController.createUser(firstName, lastName, homeAddress, password, email);
        return userJson;
    }

    @Override
    public String fetchUserAccount(String userId) {
        return userController.fetchUserAccount(userId);
    }

    @Override
    public String createPet(String name, int age) {
        return petController.createPet(name, age);
    }

    @Override
    public String fetchPetProfile(String petId) {
        return petController.fetchPetProfile(petId);
    }
}
