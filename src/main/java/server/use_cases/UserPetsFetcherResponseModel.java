package server.use_cases;

import java.util.List;

/**
 * An object defining the request type for
 * UserPetsFetcherInputBoundary.fetchUserPets
 */
public class UserPetsFetcherResponseModel {

    private final boolean isSuccess;
    private final List<String> petIds;

    public UserPetsFetcherResponseModel(boolean isSuccess, List<String> petIds) {
        this.isSuccess = isSuccess;
        this.petIds = petIds;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public List<String> getPetIds() {
        return petIds;
    }
}
