package server.drivers.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.controllers.IUserController;
import server.drivers.http.requestBody.*;
import server.use_cases.ResponseModel;
import server.adapters.UseCaseOutputBoundary;

import javax.servlet.http.HttpServletRequest;

/**
 * A gateway that makes a connection between an HTTP API back-end (as input)
 * and the controllers in our program.
 */
@RestController
@RequestMapping("/users")
public class UserWebGateway extends WebGateway {

    private final IUserController userController;

    public UserWebGateway(IUserController userController, UseCaseOutputBoundary presenter) {
        super(presenter);
        this.userController = userController;
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody CreateUserRequestBody requestBody) {
        ResponseModel response = userController.createUser(
                requestBody.getFirstName(),
                requestBody.getLastName(),
                requestBody.getCurrentAddress(),
                requestBody.getCurrentCity(),
                requestBody.getPassword(),
                requestBody.getEmailAddress()
        );

        return getResponseEntity(response);
    }

    @GetMapping("/account")
    public ResponseEntity getUserAccount(HttpServletRequest req, @RequestParam String userId) {
        ResponseModel response = userController.fetchUserAccount(
                false,
                req.getHeader("userId"),
                userId
        );

        return getResponseEntity(response);
    }

    @PostMapping("/editaccount")
    public ResponseEntity editUserAccount(HttpServletRequest req, @RequestBody EditUserAccountRequestBody requestBody) {
        ResponseModel response = userController.editUserAccount(
                false,
                req.getHeader("userId"),
                requestBody.getUserId(),
                requestBody.getNewFirstName(),
                requestBody.getNewLastName(),
                requestBody.getNewAddress(),
                requestBody.getNewCity(),
                requestBody.getNewPassword(),
                requestBody.getNewEmail()
        );

        return getResponseEntity(response);
    }

    @PostMapping("/setprofileimage")
    public ResponseEntity editProfileImage(HttpServletRequest req, @RequestBody SetUserProfileImageRequestBody requestBody) {
        ResponseModel response = userController.setUserProfile(req.getHeader("userId"), requestBody.getBase64Encoded());
        return getResponseEntity(response);
    }

    @GetMapping("/profile")
    public ResponseEntity getUserProfile(HttpServletRequest req, @RequestParam String userId) {
        ResponseModel response = userController.fetchUserProfile(false, req.getHeader("userId"), userId);
        return getResponseEntity(response);
    }

    @PostMapping("/editprofile")
    public ResponseEntity editUserProfile(HttpServletRequest req, @RequestBody EditUserProfileRequestBody requestBody) {
        ResponseModel response = userController.editUserProfile(
                false,
                req.getHeader("userId"),
                requestBody.getUserId(),
                requestBody.getNewBiography(),
                requestBody.getNewPhoneNumber(),
                requestBody.getNewInstagram(),
                requestBody.getNewFacebook()
        );

        return getResponseEntity(response);
    }

    @GetMapping("/fetchpets")
    public ResponseEntity fetchUserPets(HttpServletRequest req, @RequestParam String userId) {
        ResponseModel response = userController.fetchUserPets(false, req.getHeader("userId"), userId);
        return getResponseEntity(response);
    }

}