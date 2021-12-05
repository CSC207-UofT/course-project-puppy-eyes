package server.drivers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import server.drivers.IImageService;
import server.drivers.dbEntities.ImageDatabaseEntity;
import server.use_cases.repo_abstracts.IImageRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ImageRepository implements IImageRepository {

    private final JpaImageRepository repository;

    @Autowired
    @Qualifier("imageService")
    private IImageService imageService;

    public ImageRepository(JpaImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public String fetchUserProfileImageLink(int userId) {
        return fetchImageLink(userId, "USER_PROFILE");
    }

    @Override
    public String fetchPetProfileImageLink(int petId) {
        return fetchImageLink(petId, "PET_PROFILE");
    }

    private String fetchImageLink(int ownerId, String type) {
        Optional<ImageDatabaseEntity> searchResult = repository.findByOwnerIdAndType(ownerId, type);

        if (searchResult.isEmpty()) {
            return null;
        }

        ImageDatabaseEntity image = searchResult.get();
        return image.getUrl();
    }

    @Override
    public boolean setPetProfileImage(int ownerId, String imageId, String url) {
        return setProfileImage(ownerId, imageId, url, "PET_PROFILE");
    }

    @Override
    public boolean setUserProfileImage(int ownerId, String imageId, String url) {
        return setProfileImage(ownerId, imageId, url, "USER_PROFILE");
    }

    private boolean setProfileImage(int ownerId, String imageId, String url, String type) {
        Optional<ImageDatabaseEntity> searchResult = repository.findByOwnerId(ownerId);

        // If the user doesn't have a profile image
        if (searchResult.isEmpty()) {
            ImageDatabaseEntity image = new ImageDatabaseEntity(imageId, ownerId, url, type);
            repository.save(image);
            return true;
        }

        ImageDatabaseEntity image = searchResult.get();
        // Delete the existing image from the image server
        imageService.deleteImage(image.getAssetId());
        image.setUrl(url);
        image.setAssetId(imageId);
        repository.save(image);
        return true;
    }

}
