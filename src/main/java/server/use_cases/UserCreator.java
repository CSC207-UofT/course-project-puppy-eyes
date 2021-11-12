package server.use_cases;

import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;

/**
 * A use case responsible for creating a new user.
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
        User newUser = new User(request.getFirstName(),
                request.getLastName(),
                request.getCurrentAddress(),
                request.getCurrentCity(),
                request.getPassword(),
                request.getEmail()) {
        };

        int id = userRepository.createUser(newUser.getFirstName(), newUser.getLastName(), newUser.getCurrentAddress(),
                newUser.getCurrentCity(), newUser.getPasswordHash(), newUser.getContactInfo().getEmail());

        newUser.setId(id);


        // Return a UserCreatorResponseModel
        // TODO: Introduce cases where isSuccess is false
        // TODO: Replace isSuccess (everywhere) with an enum
        return new UserCreatorResponseModel(true,
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getCurrentAddress(),
                newUser.getCurrentCity(),
                newUser.getContactInfo().getEmail(),
                ((Integer) newUser.getId()).toString()
        );
    }
}
