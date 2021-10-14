/**
 * A use case class that is called by UserController class, and calls User class.
 */
public class UserCreator {

    /**
     * Return a UserCreatorResponseModel that contains User's basic information.
     * No direct User object is returned.
     * @param user a UserCreatorRequestModel
     * @return a UserCreatorResponseModel
     */
    public UserCreatorResponseModel createUser(UserCreatorRequestModel user) {

        // Creating a User
        User newUser = new User(user.getFirstName(),
                user.getLastName(),
                user.getHomeAddress(),
                user.getPassword(),
                user.getAddress()) {
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
