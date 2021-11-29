package server.use_cases.profile_image_fetcher;

import server.use_cases.ResponseData;

public class ProfileImageFetcherResponseModel extends ResponseData {

    private final String url;

    public ProfileImageFetcherResponseModel(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

}
