package server.drivers;

import org.springframework.stereotype.Component;
import server.controllers.IAPIGateway;
import server.controllers.IPetController;
import server.controllers.ISessionController;
import server.controllers.IUserController;

/**
 * A gateway that makes implements the IAPIGateway interfaces by calling the controllers.
 */
@Component
public class APIGateway implements IAPIGateway {

    private final IUserController userController;
    private final IPetController petController;
    private final ISessionController sessionController;

    public APIGateway(IUserController userController, IPetController petController,
                      ISessionController sessionController) {
        this.userController = userController;
        this.petController = petController;
        this.sessionController = sessionController;
    }

    @Override
    public String createUser(String firstName, String lastName, String currentAddress, String currentCity,
                             String password, String email) {
        String userJson = userController.createUser(firstName, lastName, currentAddress, currentCity, password, email);
        return userJson;
    }

    @Override
    public String fetchUserAccount(boolean fromTerminal, String headerUserId, String userId) {
        return userController.fetchUserAccount(fromTerminal, headerUserId, userId);
    }

    @Override
    public String editUserAccount(boolean fromTerminal, String headerUserId, String userId, String newFirstName, String newLastName,
                                  String newAddress, String newCity, String newPassword, String newEmail) {
        return userController.editUserAccount(fromTerminal, headerUserId, userId, newFirstName,newLastName, newAddress,
                newCity, newPassword, newEmail);
    }

    @Override
    public String fetchUserProfile(String userId) {
        return userController.fetchUserProfile(userId);
    }

    @Override
    public String editUserProfile(boolean fromTerminal, String headerUserId, String userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook) {
        return userController.editUserProfile(fromTerminal, headerUserId, userId, newBiography, newPhoneNumber, newInstagram, newFacebook);
    }

    @Override
    public String createPet(boolean fromTerminal, String headerUserId, String userId, String name, int age, String breed, String biography) {
        return petController.createPet(fromTerminal, headerUserId, userId, name, age, breed, biography);
    }

    @Override
    public String fetchPetProfile(boolean fromTerminal, String headerUserId, String petId) {
        return petController.fetchPetProfile(fromTerminal, headerUserId, petId);
    }

    @Override
    public String editPet(boolean fromTerminal, String headerUserId, String petId, String newName, int newAge, String newBreed, String newBiography) {
        return petController.editPet(fromTerminal, headerUserId, petId, newName, newAge, newBreed, newBiography);
    }

    @Override
    public String generatePotentialMatches(boolean fromTerminal, String headerUserId, int petId) {
        return petController.generatePotentialMatches(fromTerminal, headerUserId, String.valueOf(petId));
    }

    @Override
    public String swipePets(boolean fromTerminal, String headerUserId, int pet1Id, int pet2Id) {
        return petController.swipePets(fromTerminal, headerUserId, pet1Id, pet2Id);
    }

    @Override
    public String unswipePets(boolean fromTerminal, String headerUserId, int pet1Id, int pet2Id) {
        return petController.unswipePets(fromTerminal, headerUserId, pet1Id, pet2Id);
    }

    @Override
    public String rejectPets(boolean fromTerminal, String headerUserId, int pet1Id, int pet2Id) {
        return petController.rejectPets(fromTerminal, headerUserId, pet1Id, pet2Id);
    }

    @Override
    public String fetchPetSwipes(boolean fromTerminal, String headerUserId, int petId) {
        return petController.fetchPetSwipes(fromTerminal, headerUserId, petId);
    }

    @Override
    public String fetchPetMatches(boolean fromTerminal, String headerUserId, int petId) {
        return petController.fetchPetMatches(fromTerminal, headerUserId, petId);
    }

    @Override
    public String fetchUserPets(boolean fromTerminal, String headerUserId, int userId) {
        return userController.fetchUserPets(fromTerminal, headerUserId, userId);
    }

    @Override
    public String generateJwt(String email, String password) {
        return sessionController.generateJwt(email, password);
    }

}
