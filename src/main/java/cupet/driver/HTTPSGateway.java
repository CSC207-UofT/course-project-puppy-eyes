package cupet.driver;

import com.fasterxml.jackson.core.JsonProcessingException;
import cupet.APIGateway;
import cupet.IUserController;
import cupet.IUserRepository;
import cupet.UserController;
import cupet.driver.dbEntities.UserDatabaseEntity;
import cupet.driver.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HTTPSGateway implements APIGateway {
    private final IUserRepository userRepository;
    private final IUserController userController;

    // Inject all the repositories into the constructor
    public HTTPSGateway(IUserRepository userRepository, IUserController userController) {
        this.userRepository = userRepository;

        // Inject user repository into UserController
        this.userController = userController;
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
//        return userRepository.findAll();
        return null;
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