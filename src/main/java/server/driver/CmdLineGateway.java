package server.driver;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import server.APIGateway;
import server.JSONPresenter;
import server.UserController;
import server.driver.dbEntities.UserDatabaseEntity;
import server.driver.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class CmdLineGateway implements APIGateway {

    private final UserRepository userRepository;

    private UserController userController;

    public CmdLineGateway(UserRepository userRepository) {
        this.userRepository = userRepository;

        this.userController = new UserController(userRepository);
    }

    // Note: This method is not defined by APIGateway (yet)
    // This method is only for testing purposes
    // TODO make this route adhere to clean architecture
    public List<String> getAllUsers() throws JsonProcessingException {
        JSONPresenter string = new JSONPresenter();

        // Fetch raw Db Entities from Repository
        List<UserDatabaseEntity> dbUsers = userRepository.findAll();
        List<String> users = new ArrayList<>();

        // Convert each Db Entity into a JSON string
        for (UserDatabaseEntity dbUser : dbUsers) {
            users.add(string.toJSON(dbUser));
        }

        return users;
    }

    @Override
    public String createUser(String firstName, String lastName, String homeAddress, String password, String email) throws JsonProcessingException {
        String userJson = userController.createUser(firstName, lastName, homeAddress, password, email);
        return userJson;
    }
}
