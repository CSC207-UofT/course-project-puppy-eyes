package server.drivers.cmd;

import org.springframework.stereotype.Component;
import server.controllers.APIGateway;
import server.controllers.IUserController;
import server.use_cases.repo_abstracts.IUserRepository;

import java.util.List;

@Component
public class CmdLineGateway implements APIGateway {

    private final IUserRepository userRepository;
    private final IUserController userController;

    // Inject all the repositories into the constructor
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
