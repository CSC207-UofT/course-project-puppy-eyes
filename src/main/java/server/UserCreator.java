package server;

/**
 * A use case class that is called by UserController class, and calls User class.
 */
public class UserCreator {

    private UserRepositoryInterface userRepository;

    // Inject repository interface as a dependency
    public UserCreator(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

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
                user.getEmailAddress()) {
            // TODO: Remove override addPet after implementing the method
            @Override
            public boolean addPet(Pet pet) {
                return false;
            }
        };

        // Push the new User into the repository
        userRepository.createUser(newUser.getFirstName(), newUser.getLastName(), newUser.getHomeAddress(),
                newUser.getPasswordHash(), newUser.getContactInfo().getEmail());

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