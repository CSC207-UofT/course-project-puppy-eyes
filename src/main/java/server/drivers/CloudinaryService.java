package server.drivers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Implement the Cloudinary API to host images.
 */
@Component
public class CloudinaryService implements IImageService {

    @Value("${cloudinary.cloud.name}")
    private String cloudName;

    @Value("${cloudinary.api.key}")
    private String apiKey;

    @Value("${cloudinary.api.secret}")
    private String apiSecret;

    private Cloudinary getCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }

    @Override
    public ImageUploadResponse uploadBase64(String base64) {
        Cloudinary cloudinary = getCloudinary();

        try {
            Map<String, String> cloudinaryResponse = cloudinary.uploader().upload(base64, ObjectUtils.emptyMap());
            ImageUploadResponse response = new ImageUploadResponse(cloudinaryResponse.get("public_id"), cloudinaryResponse.get("url"));
            return response;
        } catch (IOException exception) {
            System.out.println(exception);
            return null;
        }
    }

    @Override
    public boolean deleteImage(String imageId) {
        Cloudinary cloudinary = getCloudinary();

        try {
            cloudinary.uploader().destroy(imageId, ObjectUtils.emptyMap());
            return true;
        } catch (IOException exception) {
            System.out.println(exception);
            return false;
        }
    }

}
