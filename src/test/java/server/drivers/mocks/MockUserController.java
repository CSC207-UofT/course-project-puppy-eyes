package server.drivers.mocks;

import server.controllers.IUserController;
import server.use_cases.ResponseModel;

public class MockUserController extends MockController  implements IUserController {
    @Override
    public ResponseModel createUser(String firstName, String lastName, String currentAddress, String currentCity, String password, String email) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel fetchUserAccount(boolean fromTerminal, String headerUserId, String userId) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel fetchUserPets(boolean fromTerminal, String headerUserId, String userId) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel editUserAccount(boolean fromTerminal, String headerUserId, String userId, String newFirstName, String newLastName, String newAddress, String newCity, String newPassword, String newEmail) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel fetchUserProfile(boolean fromTerminal, String headerUserId, String userId) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel editUserProfile(boolean fromTerminal, String headerUserId, String userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel setUserProfile(String headerUserId, String base64Encoded) {
        return makeMockResponseData();
    }
}
