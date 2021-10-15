package server;
/**
 * A use case class that is called by UserController class, and calls User class.
 */
public class UserCreator implements UserCreatorInputBoundary{

    IUserRepository userRepository;

    public UserCreator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        };

        int id = userRepository.createUser(newUser.getFirstName(), newUser.getLastName(), newUser.getHomeAddress(),
                newUser.getPasswordHash(), newUser.getContactInfo().getEmail());

        newUser.setId(id);


        // Return a UserCreatorResponseModel
        // TODO: Replace isSuccess after implementing the isSuccess method
        return new UserCreatorResponseModel(true,
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getHomeAddress(),
                newUser.getContactInfo().getEmail(),
                ((Integer) newUser.getId()).toString()
        );
    }
}
