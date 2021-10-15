package cupet;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * An interface adapter class that calls UserCreator class, and implements a method createUser.
*/
public class UserController implements IUserController {

    private UserRepositoryInterface userRepository;

    public UserController(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Return an instance of UserCreator in JSON string format using the toString method in JSONPresenter
     * @param firstName is the first name
     * @param lastName is the last name
     * @param homeAddress is the home address
     * @param password is the password
     * @param email is the email
     * @return the newUser in String
     */
    @Override
    public String createUser(String firstName, String lastName, String homeAddress, String password, String email) throws JsonProcessingException {

        // Creating a UserCreatorRequestModel called newRequestModel with input
        UserCreatorRequestModel newRequestModel = new UserCreatorRequestModel(firstName, lastName, email,
                homeAddress, password);

        // Call UserCreator class and use method createUser using neRequestModel to create a UserCreatorResponseModel
        // named newResponseModel
        UserCreator newUser = new UserCreator();
        UserCreatorResponseModel newResponseModel = newUser.createUser(newRequestModel);

        // Call JSONPresenter class and use toJSON method to return the newUser in JSON String form
        JSONPresenter string = new JSONPresenter();
        return string.toJSON(newResponseModel);
    }
}