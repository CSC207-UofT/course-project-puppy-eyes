package server.drivers;

/**
 * An interface that defines methods used for uploading and deleting images.
 */
public interface IImageService {

    /**
     * Upload a base64 image to a remote server.
     *
     * @param base64 the image represented in Base64
     * @return the upload response
     */
    ImageUploadResponse uploadBase64(String base64);

    /**
     * Delete an image from a remote server given its id.
     *
     * @param imageId the id of the image
     * @return true if successful, else false
     */
    boolean deleteImage(String imageId);

}

