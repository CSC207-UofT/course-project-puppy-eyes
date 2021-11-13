package server.drivers.http;

import org.springframework.web.bind.annotation.*;
import server.controllers.APIGateway;
import server.controllers.IPetController;
import server.controllers.IUserController;
import server.controllers.ISessionController;
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
    public HTTPSGateway(IUserController userController, IPetController petController,
                        ISessionController sessionController) {
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
        return createPet(requestBody.getUserId(), requestBody.getName(), requestBody.getAge(), requestBody.getBreed(), requestBody.getBiography());
    }

    @Override
    public String createPet(int userId, String name, int age, String breed, String biography) {
        return petController.createPet(userId, name, age, breed, biography);
    }

    @GetMapping("/pets/profile")
    public String getPetProfile(@RequestBody FetchPetProfileRequestBody requestBody) {
        return fetchPetProfile(requestBody.getPetId());
    }

    @Override
    public String fetchPetProfile(String petId) {
        return petController.fetchPetProfile(petId);
    }

    @PostMapping("/pets/edit")
    public String editPet(@RequestBody EditPetRequestBody requestBody) {
        return editPet(requestBody.getPetId(), requestBody.getNewName(), requestBody.getNewAge(),
                requestBody.getNewBreed(), requestBody.getNewBiography());
    }

    @Override
    public String editPet(String petId, String newName, int newAge, String newBreed, String newBiography) {
        return petController.editPet(petId, newName, newAge, newBreed, newBiography);
    }

    @PostMapping("/auth/login")
    public String generateJwt(@RequestBody LoginRequestBody loginRequest) {
        return sessionController.generateJwt(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @Override
    public String swipePets(int pet1Id, int pet2Id) {
        return petController.swipePets(pet1Id, pet2Id);
    }

    @PostMapping("/pets/swipe")
    public String swipePets(@RequestBody SwipePetsRequestBody requestBody) {
        return swipePets(requestBody.getFirstPetId(), requestBody.getSecondPetId());
    }

    @Override
    public String unswipePets(int pet1Id, int pet2Id) {
        return petController.unswipePets(pet1Id, pet2Id);
    }


    @PostMapping("/pets/unswipe")
    public String unswipePets(@RequestBody UnswipePetsRequestBody requestBody) {
        return unswipePets(requestBody.getFirstPetId(), requestBody.getSecondPetId());
    }

    @Override
    public String rejectPets(int pet1Id, int pet2Id) {
        return petController.rejectPets(pet1Id, pet2Id);
    }

    @PostMapping("/pets/reject")
    public String rejectPets(@RequestBody RejectPetsRequestBody requestBody) {
        return rejectPets(requestBody.getFirstPetId(), requestBody.getSecondPetId());
    }

    @Override
    public String fetchPetSwipes(int petId) {
        return fetchPetSwipes(petId);
    }

    @GetMapping("/pets/fetchswipes")
    public String fetchPetSwipes(@RequestBody FetchPetSwipesRequestBody requestBody) {
        return fetchPetSwipes(requestBody.getPetId());
    }

    @Override
    public String fetchPetMatches(int petId) {
        return fetchPetMatches(petId);
    }

    @GetMapping("/pets/fetchmatches")
    public String fetchPetMatches(@RequestBody FetchPetMatchesRequestBody requestBody) {
        return fetchPetMatches(requestBody.getPetId());
    }

    @Override
    public String fetchUserPets(int userId) {
        return userController.fetchUserPets(userId);
    }

    @GetMapping("/users/fetchpets")
    public String fetchUserPets(@RequestBody FetchUserPetsRequestBody requestBody) {
        return fetchUserPets(requestBody.getUserId());
    }

}