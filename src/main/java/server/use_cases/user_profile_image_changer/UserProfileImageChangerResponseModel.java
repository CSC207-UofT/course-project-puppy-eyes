package server.use_cases.user_profile_image_changer;

import server.use_cases.ResponseData;

public class UserProfileImageChangerResponseModel extends ResponseData {

    private final String url, assetId;

    public UserProfileImageChangerResponseModel(String assetId, String url) {
        this.url = url;
        this.assetId = assetId;
    }

    public String getUrl() {
        return this.url;
    }

    public String getAssetId() {
        return this.assetId;
    }

}
