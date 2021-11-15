package server.use_cases;

import server.use_cases.repo_abstracts.ResponseData;

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
