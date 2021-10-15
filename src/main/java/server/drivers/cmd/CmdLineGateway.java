package server.drivers.cmd;

import org.springframework.stereotype.Component;
import server.controllers.APIGateway;
import server.controllers.IUserController;
import server.use_cases.repo_abstracts.IUserRepository;

import java.util.List;

/**
 * A gateway that makes a connection between the command line (as input)
 * and the controllers in our program. This class is complementary to the CmdLineRunner class.
 */
@Component
public class CmdLineGateway implements APIGateway {

    private final IUserRepository userRepository;
    private final IUserController userController;

    public CmdLineGateway(IUserRepository userRepository, IUserController userController) {
        this.userRepository = userRepository;
        this.userController = userController;
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
}
