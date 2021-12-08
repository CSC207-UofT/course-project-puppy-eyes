package server.drivers.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import server.entities.User;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the UserRepository. This class is non-functional, so we test that no runtime exceptions are thrown.
 */
class UserRepositoryTest {
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository(Mockito.mock(JpaUserRepository.class));
    }

    @Test
    void testCreateUser() {
        userRepository.createUser(
                new User("first", "last", "1234 addr", "city", "password", "email@email.com"));
    }

    @Test
    void testFetchUser() {
        userRepository.fetchUser(0);
    }

    @Test
    void testEditUserAccount() {
        userRepository.editUserAccount(0, "first", "last",
                "1234 addr", "1234 city", "password",
                "email", "lat", "long");
    }

    @Test
    void testEditUserProfile() {
        userRepository.editUserProfile(0, "bio", "phone",
                "insta", "facebook");
    }

    @Test
    void testFetchAllUsers() {
        userRepository.fetchAllUsers();
    }

    @Test
    void testFetchIdFromEmail() {
        userRepository.fetchIdFromEmail("email");
    }

    @Test
    void testFetchUserPets() {
        userRepository.fetchUserPets(0);
    }
}