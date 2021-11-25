package server.drivers;

/**
 * A class representing the response data from uploading an image to a remote server.
 */
public class ImageUploadResponse {

    private final String assetId, url;

    public ImageUploadResponse(String assetId, String url) {
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
