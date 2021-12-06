package server.use_cases.user_use_cases.user_profile_image_changer;

import server.use_cases.ResponseData;

/**
 * An object defining the response type for
 * UserProfileImageChanger.changeProfileImage
 */
public class UserProfileImageChangerResponseModel extends ResponseData {

    private final String url, assetId;

    public UserProfileImageChangerResponseModel(String assetId, String url) {
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
