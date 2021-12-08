package server.drivers.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the PetRepository. This class is non-functional, so we test that no runtime exceptions are thrown.
 */
class PetRepositoryTest {
    PetRepository petRepository;

    @BeforeEach
    void setUp() {
        petRepository = new PetRepository(Mockito.mock(JpaPetRepository.class),
                new RelationRepository(Mockito.mock(JpaRelationRepository.class)));
    }

    @Test
    void testCreatePet() {
        petRepository.createPet(0, "name", 4, "breed", "bio");
    }

    @Test
    void testFetchPet() {
        petRepository.fetchPet(0);
    }

    @Test
    void testEditPet() {
        petRepository.editPet(0, "name", 4, "breed", "bio");
    }

    @Test
    void testSwipePets() {
        petRepository.swipePets(0, 1);
    }

    @Test
    void testMatchPets() {
        petRepository.matchPets(0, 1);
    }

    @Test
    void testUnswipePets() {
        petRepository.unswipePets(0, 1);
    }

    @Test
    void testRejectPets() {
        petRepository.rejectPets(0, 1);
    }

    @Test
    void testUnmatchPets() {
        petRepository.unmatchPets(0, 1);
    }

    @Test
    void testFetchSwipedOn() {
        petRepository.fetchSwipedOn(0);

    }

    @Test
    void testFetchRejected() {
        petRepository.fetchRejected(0);
    }

    @Test
    void testFetchMatches() {
        petRepository.fetchMatches(0);
    }
}