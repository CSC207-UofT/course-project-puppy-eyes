package server.drivers.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test the ImageRepository. This class is non-functional, so we test that no runtime exceptions are thrown.
 */
class ImageRepositoryTest {
    static JpaImageRepository jpaImageRepository;
    static ImageRepository imageRepository;

    @BeforeAll
    static void setUp() {
        jpaImageRepository = Mockito.mock(JpaImageRepository.class);
        imageRepository = new ImageRepository(jpaImageRepository);
    }

    @Test
    public void testSetPetProfileImage() {
        imageRepository.setPetProfileImage(0, "test img", "test url");
    }

    @Test
    public void testSetUserProfileImage() {
        imageRepository.setUserProfileImage(0, "test img", "test url");
    }

    @Test
    public void testFetchUserProfileImageLink() {
        imageRepository.fetchUserProfileImageLink(0);
    }

    @Test
    public void testFetchPetProfileImageLink() {
        imageRepository.fetchPetProfileImageLink(0);
    }

}