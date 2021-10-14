/**
 * An interface adapter class that calls UserCreator class, and implements a method createUser.
*/
public class UserController implements IUserController{
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
    public String createUser(String firstName, String lastName, String homeAddress, String password, String email) {

        // Creating a UserCreatorRequestModel called newModel with input
        UserCreatorRequestModel newModel = new UserCreatorRequestModel(firstName, lastName, email,
                homeAddress, password);

        // Call UserCreator class and use method CreateUser using newModel to create a new user called newUser
        UserCreator newUser = new UserCreator();
        newUser.createUser(newModel);

        // Call JSONPresenter class and use toJSON method to return the newUser in JSON String form
        JSONPresenter string = new JSONPresenter();
        return string.toJSON(newUser);
    }
}