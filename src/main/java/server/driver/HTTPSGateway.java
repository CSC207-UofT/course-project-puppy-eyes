package server.driver;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import server.*;
import server.driver.dbEntities.UserDatabaseEntity;
import server.driver.repository.UserRepository;

import java.util.List;

@RestController
public class HTTPSGateway implements APIGateway {

    private final UserRepository userRepository;

    private UserController userController;

    // Inject all the repositories into the constructor
    public HTTPSGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
        // Create a new instance of UserController
        this.userController = new UserController(userRepository);
    }

    @GetMapping("/")
    public String landingMessage() {
        return "Welcome to Cupet";
    }

    // Note: This method is not defined by APIGateway (yet)
    // This method is only for testing purposes
    // TODO make this route adhere to clean architecture
    @GetMapping("/users/all")
    public List<UserDatabaseEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users/create")
    public String createUser(@RequestBody CreateUserRequestBody requestBody) throws JsonProcessingException {
        return createUser(requestBody.getFirstName(), requestBody.getLastName(), requestBody.getHomeAddress(),
                requestBody.getPassword(), requestBody.getEmailAddress());
    }

    @Override
    public String createUser(String firstName, String lastName, String homeAddress, String password, String email)
            throws JsonProcessingException {
        String userJson = userController.createUser(firstName, lastName, homeAddress, password, email);
        // TODO remove print statement later
        System.out.println(userJson);
        return userJson;
    }


}