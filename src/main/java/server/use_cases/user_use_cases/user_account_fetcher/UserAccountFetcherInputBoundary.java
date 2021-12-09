package server.use_cases.user_use_cases.user_account_fetcher;

import server.use_cases.ResponseModel;

/**
 * An input boundary for the "fetch user account" use case.
 */
public interface UserAccountFetcherInputBoundary {

    /**
     * Given a request object, perform the use case and return a ResponseModel
     *
     * @param request the request object
     * @return a ResponseModel object
     */
    ResponseModel fetchUserAccount(UserAccountFetcherRequestModel request);

}
