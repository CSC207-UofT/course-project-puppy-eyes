package server.drivers.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.controllers.IPetController;
import server.drivers.http.requestBody.*;
import server.use_cases.repo_abstracts.ResponseModel;
import server.use_cases.repo_abstracts.UseCaseOutputBoundary;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pets")
public class PetWebGateway extends WebGateway {

    private final IPetController petController;

    public PetWebGateway(IPetController petController, UseCaseOutputBoundary presenter) {
        super(presenter);
        this.petController = petController;
    }

    @PostMapping("/pets/create")
    public ResponseEntity createPet(HttpServletRequest req, @RequestBody CreatePetRequestBody requestBody) {
        ResponseModel response = petController.createPet(
            false,
            req.getHeader("userId"),
            requestBody.getUserId(),
            requestBody.getName(),
            requestBody.getAge(),
            requestBody.getBreed(),
            requestBody.getBiography()
        );

        return getResponseEntity(response);
    }

    @GetMapping("/pets/profile")
    public ResponseEntity getPetProfile(HttpServletRequest req, @RequestParam String petId) {
        ResponseModel response = petController.fetchPetProfile(false, req.getHeader("userId"), petId);

        return getResponseEntity(response);
    }

    @PostMapping("/pets/editprofile")
    public ResponseEntity editPet(HttpServletRequest req, @RequestBody EditPetRequestBody requestBody) {
        ResponseModel response = petController.editPet(
            false,
            req.getHeader("userId"),
            requestBody.getPetId(),
            requestBody.getNewName(),
            requestBody.getNewAge(),
            requestBody.getNewBreed(),
            requestBody.getNewBiography()
        );

        return getResponseEntity(response);
    }

    @PostMapping("/pets/swipe")
    public ResponseEntity swipePets(HttpServletRequest req, @RequestBody SwipePetsRequestBody requestBody) {
        ResponseModel response = petController.swipePets(
            false,
            req.getHeader("userId"),
            requestBody.getFirstPetId(),
            requestBody.getSecondPetId()
        );

        return getResponseEntity(response);
    }

    @PostMapping("/pets/unswipe")
    public ResponseEntity unswipePets(HttpServletRequest req, @RequestBody UnswipePetsRequestBody requestBody) {
        ResponseModel response = petController.unswipePets(
            false,
            req.getHeader("userId"),
            requestBody.getFirstPetId(),
            requestBody.getSecondPetId()
        );

        return getResponseEntity(response);
    }

    @PostMapping("/pets/reject")
    public ResponseEntity rejectPets(HttpServletRequest req, @RequestBody RejectPetsRequestBody requestBody) {
        ResponseModel response = petController.rejectPets(
            false,
            req.getHeader("userId"),
            requestBody.getFirstPetId(),
            requestBody.getSecondPetId()
        );

        return getResponseEntity(response);
    }

    @GetMapping("/pets/fetchswipes")
    public ResponseEntity fetchPetSwipes(HttpServletRequest req, @RequestParam String petId) {
        ResponseModel response = petController.fetchPetSwipes(false, req.getHeader("userId"), petId);
        return getResponseEntity(response);
    }

    @GetMapping("/pets/fetchmatches")
    public ResponseEntity fetchPetMatches(HttpServletRequest req, @RequestParam String petId) {
        ResponseModel response = petController.fetchPetMatches(false, req.getHeader("userId"), petId);
        return getResponseEntity(response);
    }

    @GetMapping("/generatepotentialmatches")
    public ResponseEntity generatePotentialMatches(HttpServletRequest req, @RequestParam String petId) {
        ResponseModel response = petController.generatePotentialMatches(false, req.getHeader("userId"), petId);
        return getResponseEntity(response);
    }

}
