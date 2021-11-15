package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

/**
 * An input boundary for the "fetch user account" use case.
 */
public interface UserAccountFetcherInputBoundary {

    public ResponseModel fetchUserAccount(UserAccountFetcherRequestModel request);

}
