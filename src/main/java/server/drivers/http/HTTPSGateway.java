package server.drivers.http;

import org.springframework.web.bind.annotation.*;
import server.controllers.APIGateway;
import server.controllers.IPetController;
import server.controllers.IUserController;
import server.controllers.ISessionController;
import server.controllers.IUserController;
import javax.servlet.http.HttpServletRequest;

/**
 * A gateway that makes a connection between an HTTP API back-end (as input)
 * and the controllers in our program.
 */
@RestController
public class HTTPSGateway implements APIGateway {

    private final IUserController userController;
    private final IPetController petController;
    private final ISessionController sessionController;

    // Inject all the repositories into the constructor
    public HTTPSGateway(IUserController userController, IPetController petController, ISessionController sessionController) {
        // Inject repositories into related controllers
        this.userController = userController;
        this.petController = petController;;
        this.sessionController = sessionController;
    }

    @GetMapping("/")
    public String landingMessage() {
        return "Welcome to Cupet";
    }

    @GetMapping("/authtest")
    public String authTest(HttpServletRequest request) {
        return "You are authenticated! Welcome " + request.getHeader("userId");
    }

    @PostMapping("/users/create")
    public String createUser(@RequestBody CreateUserRequestBody requestBody) {
        return createUser(requestBody.getFirstName(), requestBody.getLastName(), requestBody.getCurrentAddress(),
                requestBody.getCurrentCity(), requestBody.getPassword(), requestBody.getEmailAddress());
    }

    @Override
    public String createUser(String firstName, String lastName, String currentAddress, String currentCity, String password, String email) {
        String userJson = userController.createUser(firstName, lastName, currentAddress, currentCity, password, email);
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
        return userController.fetchUserAccount(userId);
    }

    @PostMapping("/pets/create")
    public String createPet(@RequestBody CreatePetRequestBody requestBody) {
        return createPet(requestBody.getUserId(), requestBody.getName(), requestBody.getAge());
    }

    @Override
    public String createPet(int userId, String name, int age) {
        return petController.createPet(userId, name, age);
    }

    @PostMapping("/auth/login")
    public String generateJwt(@RequestBody LoginRequestBody loginRequest) {
        return sessionController.generateJwt(loginRequest.getEmail(), loginRequest.getPassword());
    }
    
}