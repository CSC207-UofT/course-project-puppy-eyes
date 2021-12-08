package server.drivers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import server.adapters.UseCaseOutputBoundary;
import server.drivers.http.PetWebGateway;
import server.drivers.http.requestBody.*;
import server.drivers.mocks.MockPetController;

import javax.servlet.http.HttpServletRequest;

/**
 * Test the PetWebGateway. This class is non-functional, so we test that no runtime exceptions are thrown.
 */
public class TestPetWebGateway {
    public PetWebGateway petWebGateway;
    public HttpServletRequest req;

    @BeforeEach
    public void setUp(){
        UseCaseOutputBoundary outputBoundary = Mockito.mock(UseCaseOutputBoundary.class);

        req = Mockito.mock(HttpServletRequest.class);
        petWebGateway = new PetWebGateway(new MockPetController(), outputBoundary);
    }

    @Test
    public void testCreatePet() {
        petWebGateway.createPet(req, new CreatePetRequestBody(
                "test id", "test name", "test age", "test breed", "test bio"
        ));
    }

    @Test
    public void testGetPetProfile() {
        petWebGateway.getPetProfile("test id");
    }

    @Test
    public void testEditPet() {
        petWebGateway.editPet(req, new EditPetRequestBody("test id",
                "test name", "test age",
                "test breed", "test bio"));
    }

    @Test
    public void testSwipePets() {
        petWebGateway.swipePets(req, new SwipePetsRequestBody("test id 1", "test id 2"));
    }

    @Test
    public void testUnswipePets() {
        petWebGateway.unswipePets(req, new UnswipePetsRequestBody("test id 1", "test id 2"));
    }

    @Test
    public void testRejectPets() {
        petWebGateway.rejectPets(req, new RejectPetsRequestBody("test id 1", "test id 2"));
    }

    @Test
    public void testUnmatchPets() {
        petWebGateway.unmatchPets(req, new UnmatchPetsRequestBody("test id 1", "test id 2"));
    }

    @Test
    public void testFetchPetSwipes() {
        petWebGateway.fetchPetSwipes(req, "test id");
    }

    @Test
    public void testFetchPetMatches() {
        petWebGateway.fetchPetMatches(req, "test id");
    }

    @Test
    public void testGeneratePotentialMatches() {
        petWebGateway.generatePotentialMatches(req, "test id");
    }

    @Test
    public void testEditProfileImage() {
        petWebGateway.editProfileImage(
                req, new SetPetProfileImageRequestBody("test id", "test b64"));
    }
}
