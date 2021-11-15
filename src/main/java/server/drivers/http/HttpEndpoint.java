package server.drivers.http;

import org.springframework.web.bind.annotation.*;
import server.drivers.APIGateway;
import server.drivers.http.requestBody.*;

import javax.servlet.http.HttpServletRequest;

/**
 * A gateway that makes a connection between an HTTP API back-end (as input)
 * and the controllers in our program.
 */
@RestController
public class HttpEndpoint {

    private final APIGateway gateway;

    public HttpEndpoint(APIGateway gateway) {
        this.gateway = gateway;
    }

    @GetMapping("/")
    public String landingMessage() {
        return "Welcome to Cupet";
    }

    @GetMapping("/authtest")
    public String authTest(HttpServletRequest req) {
        return "You are authenticated! Welcome " + req.getHeader("userId");
    }

    @PostMapping("/users/create")
    public String createUser(@RequestBody CreateUserRequestBody requestBody) {
        return gateway.createUser(requestBody.getFirstName(), requestBody.getLastName(), requestBody.getCurrentAddress(),
                requestBody.getCurrentCity(), requestBody.getPassword(), requestBody.getEmailAddress());
    }

    @GetMapping("/users/account")
    public String getUserAccount(HttpServletRequest req, @RequestParam String userId) {
        return gateway.fetchUserAccount(false, req.getHeader("userId"), userId);
    }

    @PostMapping("/users/editaccount")
    public String editUserAccount(HttpServletRequest req, @RequestBody EditUserAccountRequestBody requestBody) {
        return gateway.editUserAccount(false, req.getHeader("userId"), requestBody.getUserId(), requestBody.getNewFirstName(),
                requestBody.getNewLastName(), requestBody.getNewAddress(), requestBody.getNewCity(),
                requestBody.getNewPassword(), requestBody.getNewEmail());
    }

    @GetMapping("/users/profile")
    public String getUserProfile(@RequestParam String userId) {
        return gateway.fetchUserProfile(userId);
    }

    @PostMapping("/users/editprofile")
    public String editUserProfile(HttpServletRequest req, @RequestBody EditUserProfileRequestBody requestBody) {
        return gateway.editUserProfile(false, req.getHeader("userId"), requestBody.getUserId(), requestBody.getNewBiography(), requestBody.getNewPhoneNumber(),
                requestBody.getNewInstagram(), requestBody.getNewFacebook());
    }

    @PostMapping("/pets/create")
    public String createPet(HttpServletRequest req, @RequestBody CreatePetRequestBody requestBody) {
        return gateway.createPet(false, req.getHeader("userId"), requestBody.getUserId(), requestBody.getName(), requestBody.getAge(), requestBody.getBreed(),
                requestBody.getBiography());
    }

    @GetMapping("/pets/profile")
    public String getPetProfile(HttpServletRequest req, @RequestParam String petId) {
        return gateway.fetchPetProfile(false, req.getHeader("userId"), petId);
    }

    @PostMapping("/pets/editprofile")
    public String editPet(HttpServletRequest req, @RequestBody EditPetRequestBody requestBody) {
        return gateway.editPet(false, req.getHeader("userId"), requestBody.getPetId(), requestBody.getNewName(), requestBody.getNewAge(),
                requestBody.getNewBreed(), requestBody.getNewBiography());
    }

    @PostMapping("/auth/login")
    public String generateJwt(@RequestBody LoginRequestBody loginRequest) {
        return gateway.generateJwt(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/pets/swipe")
    public String swipePets(HttpServletRequest req, @RequestBody SwipePetsRequestBody requestBody) {
        return gateway.swipePets(false, req.getHeader("userId"), requestBody.getFirstPetId(), requestBody.getSecondPetId());
    }

    @PostMapping("/pets/unswipe")
    public String unswipePets(HttpServletRequest req, @RequestBody UnswipePetsRequestBody requestBody) {
        return gateway.unswipePets(false, req.getHeader("userId"), requestBody.getFirstPetId(), requestBody.getSecondPetId());
    }

    @PostMapping("/pets/reject")
    public String rejectPets(HttpServletRequest req, @RequestBody RejectPetsRequestBody requestBody) {
        return gateway.rejectPets(false, req.getHeader("userId"), requestBody.getFirstPetId(), requestBody.getSecondPetId());
    }

    @GetMapping("/pets/fetchswipes")
    public String fetchPetSwipes(HttpServletRequest req, @RequestParam String petId) {
        return gateway.fetchPetSwipes(false, req.getHeader("userId"), Integer.parseInt(petId));
    }

    @GetMapping("/pets/fetchmatches")
    public String fetchPetMatches(HttpServletRequest req, @RequestParam String petId) {
        return gateway.fetchPetMatches(false, req.getHeader("userId"), Integer.parseInt(petId));
    }

    @GetMapping("/users/fetchpets")
    public String fetchUserPets(HttpServletRequest req, @RequestParam String userId) {
        return gateway.fetchUserPets(false, req.getHeader("userId"), Integer.parseInt(userId));
    }

    @GetMapping("/users/generatepotentialmatches")
    public String generatePotentialMatches(HttpServletRequest req, @RequestParam String petId) {
        return gateway.generatePotentialMatches(false, req.getHeader("userId"), Integer.parseInt(petId));
    }

}