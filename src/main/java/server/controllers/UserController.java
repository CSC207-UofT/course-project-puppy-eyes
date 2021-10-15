package server.controllers;

import server.use_cases.*;

import java.util.HashMap;

/**
 * An interface adapter class that calls UserCreator class, and implements a method createUser.
*/
public class UserController implements IUserController {
    UserCreatorInputBoundary userCreator;
    UserAccountFetcherInputBoundary accountFetcher;
    IJSONPresenter jsonPresenter;

    public UserController(UserCreatorInputBoundary userCreator,
                          UserAccountFetcherInputBoundary accountFetcher,
                          IJSONPresenter jsonPresenter) {
        this.userCreator = userCreator;
        this.accountFetcher = accountFetcher;
        this.jsonPresenter = jsonPresenter;
    }

    /**
     * Create a new user. Return a JSON structure containing:
     * {
     *  isSuccess: "true"/"false"
     *  // If successful, then include the following
     *  firstName: give back the first name of new user
     *  lastName: give back the last name of the new user
     *  homeAddress: give back the home address of the new user
     *  email: give back the email of the new user
     * }
     *
     * @param firstName user's first name
     * @param lastName user's last name
     * @param homeAddress user's home address
     * @param password user's password
     * @param email user's email
     *
     * @return The JSON response
     */
    @Override
    public String createUser(String firstName, String lastName, String homeAddress, String password, String email) {

        // Creating a UserCreatorRequestModel called newRequestModel with input
        UserCreatorRequestModel newRequestModel = new UserCreatorRequestModel(firstName, lastName, email,
                homeAddress, password);

        UserCreatorResponseModel response = userCreator.createUser(newRequestModel);
        // Un-pack response into a map and prepare to convert to JSON
        HashMap<String, String> responseMap = new HashMap<String, String>(){{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("firstName", response.getFirstName());
            put("lastName", response.getLastName());
            put("homeAddress", response.getHomeAddress());
            put("email", response.getEmail());
            put("userId", response.getUserId());
        }};

        return jsonPresenter.toJSON(responseMap);
    }

    @Override
    public String fetchUserAccount(String userId) {
        UserAccountFetcherRequestModel request = new UserAccountFetcherRequestModel(userId);

        UserAccountFetcherResponseModel response = accountFetcher.fetchUserAccount(request);

        // Un-pack response into a map and prepare to convert to JSON
        HashMap<String, String> responseMap = new HashMap<String, String>(){{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("firstName", response.getFirstName());
            put("lastName", response.getLastName());
            put("homeAddress", response.getHomeAddress());
            put("email", response.getEmail());
        }};

        return jsonPresenter.toJSON(responseMap);
    }
}