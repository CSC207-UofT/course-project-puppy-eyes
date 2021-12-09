package server.use_cases.user_use_cases.user_pets_fetcher;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "user pets fetcher" use case
 */
public interface UserPetsFetcherInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request
     * @return a ResponseModel object
     */
    ResponseModel fetchUserPets(UserPetsFetcherRequestModel request);

}
