package server.drivers.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.controllers.APIGateway;
import server.controllers.IUserController;
import server.use_cases.repo_abstracts.IUserRepository;

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

    @PostMapping("/users/create")
    public String createUser(@RequestBody CreateUserRequestBody requestBody) {
        return createUser(requestBody.getFirstName(), requestBody.getLastName(), requestBody.getHomeAddress(),
                requestBody.getPassword(), requestBody.getEmailAddress());
    }

    @Override
    public String createUser(String firstName, String lastName, String homeAddress, String password, String email) {
        String userJson = userController.createUser(firstName, lastName, homeAddress, password, email);
        // TODO remove print statement later
        System.out.println(userJson);
        return userJson;
    }

    @GetMapping("/users/account")
    public String getUserAccount(@RequestBody FetchUserAccountRequestBody requestBody) {
        return fetchUserAccount(requestBody.getUserId());
    }

    @Override
    public String fetchUserAccount(String userId) {
        String responseJson = userController.fetchUserAccount(userId);
        return responseJson;
    }
}