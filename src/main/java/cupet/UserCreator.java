package cupet;

/**
 * A use case class that is called by UserController class, and calls User class.
 */
public class UserCreator implements UserCreatorInputBoundary{

    /**
     * Return a UserCreatorResponseModel that contains
     * the created user's basic information.
     *
     * @param request Object containing registration data of the new user.
     */
    public UserCreatorResponseModel createUser(UserCreatorRequestModel request) {
        // Creating a User
        User newUser = new User(request.getFirstName(),
                request.getLastName(),
                request.getHomeAddress(),
                request.getPassword(),
                request.getEmail()) {
            // TODO: Remove override add_pet after implementing the method
            @Override
            boolean add_pet(Pet pet) {
                return false;
            }
        };

        // Return a UserCreatorResponseModel
        // TODO: Replace isSuccess after implementing the isSuccess method
        return new UserCreatorResponseModel(true,
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getHomeAddress(),
                newUser.getPasswordHash(),
                newUser.getContactInfo().getEmail());
    }
}
