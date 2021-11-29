package server.use_cases.profile_image_fetcher;

import server.use_cases.AuthRequestModel;

public class ProfileImageFetcherRequestModel extends AuthRequestModel {

    private final boolean isUser;
    private final String ownerId;

    public ProfileImageFetcherRequestModel(String headerUserId, String ownerId, boolean isUser) {
        super(headerUserId);

        if (isUser) {
            this.setUserId(ownerId);
        }

        this.isUser = isUser;
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public boolean isUser() {
        return this.isUser;
    }

}