package server.use_cases;

import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.ResponseModel;
import server.use_cases.repo_abstracts.UserNotFoundException;

/**
 * A use case responsible for creating a new user.
 */
public class UserCreator implements UserCreatorInputBoundary {

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
    public ResponseModel createUser(UserCreatorRequestModel request) {
        // Check if user exists
        if (userRepository.fetchIdFromEmail(request.getEmail()) >= 0) {
            return new ResponseModel(false, "A user with this email already exists.");
        }

        // TODO factory method
        User newUser = new User(
            request.getFirstName(),
            request.getLastName(),
            request.getCurrentAddress(),
            request.getCurrentCity(),
            request.getPassword(),
            request.getEmail()
        ) {};

        if (!newUser.isFirstNameValid() || !newUser.isLastNameValid()) {
            return new ResponseModel(false, "Please enter a name of at least 3 characters.");
        }

        if (!newUser.isPasswordValid()) {
            return new ResponseModel(false, "Please enter a password of at least 6 characters.");
        }

        if (!newUser.getContactInfo().isEmailValid()) {
            return new ResponseModel(false, "Please enter an email of at least 5 characters.");
        }

        int id = userRepository.createUser(newUser.getFirstName(), newUser.getLastName(), newUser.getPasswordHash(), newUser.getCurrentAddress(),
                newUser.getCurrentCity(), newUser.getContactInfo().getEmail());

        newUser.setId(id);

        return new ResponseModel(
            true,
            "Successfully created new user.",
            new UserCreatorResponseModel(
                String.valueOf(id),
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getCurrentAddress(),
                newUser.getCurrentCity(),
                newUser.getContactInfo().getEmail()
            )
        );
    }
}
