package server.use_cases;

import server.use_cases.repo_abstracts.IImageRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * A dummy image repository storing values in memory.
 */
public class DummyImageRepository implements IImageRepository {

    private class Image {

        private String assetId, url;

        public Image(String assetId, String url) {
            this.assetId = assetId;
            this.url = url;
        }

        public String getAssetId() {
            return this.assetId;
        }

        public String getUrl() {
            return this.url;
        }

    }

    private Map<Integer, Image> petUrls, userUrls;

    public DummyImageRepository() {
        petUrls = new HashMap<>();
        userUrls = new HashMap<>();
    }

    @Override
    public String fetchUserProfileImageLink(int userId) {
        return userUrls.get(userId).getUrl();
    }

    @Override
    public String fetchPetProfileImageLink(int petId) {
        return petUrls.get(petId).getUrl();
    }

    @Override
    public boolean setUserProfileImage(int userId, String assetId, String url) {
        userUrls.put(userId, new Image(assetId, url));
        return true;
    }

    @Override
    public boolean setPetProfileImage(int petId, String assetId, String url) {
        petUrls.put(petId, new Image(assetId, url));
        return true;
    }
}
