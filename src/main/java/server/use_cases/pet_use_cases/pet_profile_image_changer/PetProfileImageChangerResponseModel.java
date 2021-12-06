package server.use_cases.pet_use_cases.pet_profile_image_changer;

import server.use_cases.ResponseData;

/**
 * An object defining the response type for
 * UserProfileImageChanger.changeProfileImage
 */
public class PetProfileImageChangerResponseModel extends ResponseData {

    private final String url, assetId;

    public PetProfileImageChangerResponseModel(String assetId, String url) {
        this.url = url;
        this.assetId = assetId;
    }

    /**
     * The URL of the profile image
     * @return a String URL
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * The Asset ID of the profile image
     * @return a String ID
     */
    public String getAssetId() {
        return this.assetId;
    }

}
