package server.use_cases.user_pets_fetcher;

import server.use_cases.ResponseData;

import java.util.List;

/**
 * An object defining the request type for
 * UserPetsFetcherInputBoundary.fetchUserPets
 */
public class UserPetsFetcherResponseModel extends ResponseData {

    private final List<String> petIds;

    public UserPetsFetcherResponseModel(List<String> petIds) {
        this.petIds = petIds;
    }

    public List<String> getPetIds() {
        return petIds;
    }
}
