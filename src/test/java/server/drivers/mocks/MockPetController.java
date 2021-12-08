package server.drivers.mocks;

import server.controllers.IPetController;
import server.use_cases.ResponseModel;

public class MockPetController extends MockController implements IPetController {
    @Override
    public ResponseModel createPet(boolean fromTerminal, String headerUserId, String userId, String name, String age, String breed, String biography) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel fetchPetProfile(String petId) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel editPet(boolean fromTerminal, String headerUserId, String petId, String newName, String newAge, String newBreed, String newBiography) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel rejectPets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel generatePotentialMatches(boolean fromTerminal, String headerUserId, String petId) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel swipePets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel unswipePets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel fetchPetSwipes(boolean fromTerminal, String headerUserId, String petId) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel fetchPetMatches(boolean fromTerminal, String headerUserId, String petId) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel setPetProfile(String headerUserId, String petId, String base64Encoded) {
        return makeMockResponseData();
    }

    @Override
    public ResponseModel unmatchPets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id) {
        return makeMockResponseData();
    }
}
