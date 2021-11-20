package server.use_cases;

import server.drivers.IPasswordEncryptor;
import server.entities.UserFactory;
import server.entities.User;
import server.entities.UserType;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.ResponseModel;

/**
 * A use case responsible for creating a new user.
 */
public class UserCreator implements UserCreatorInputBoundary {

    IUserRepository userRepository;
    IPasswordEncryptor passwordEncryptor;
    UserAccountValidatorInputBoundary userAccountValidator;

    public UserCreator(IUserRepository userRepository, IPasswordEncryptor passwordEncryptor,
                       UserAccountValidatorInputBoundary userAccountValidator) {
        this.userRepository = userRepository;
        this.passwordEncryptor = passwordEncryptor;
        this.userAccountValidator = userAccountValidator;
    }


    /**
     * Return a ResponseModel with holding a UserCreatorResponseModel that contains
     * the created user's basic information.
     *
     * @param request Object containing registration data of the new user.
     */
    public ResponseModel createUser(UserCreatorRequestModel request) {
        // Check if user exists
        if (userRepository.fetchIdFromEmail(request.getEmail()) >= 0) {
            return new ResponseModel(false, "A user with this email already exists.");
        }

        // Check inputs
        ResponseModel verifyInputsResponse = userAccountValidator.validateAccount(
            new UserAccountValidatorRequestModel(
                request.getFirstName(),
                request.getLastName(),
                request.getCurrentCity(),
                request.getPassword(),
                request.getEmail()
            )
        );

        if (!verifyInputsResponse.isSuccess()) {
            return verifyInputsResponse;
        }

        UserFactory userFactory = new UserFactory();

        User newUser = userFactory.createUser(
            UserType.COMMON_USER,
            request.getFirstName(),
            request.getLastName(),
            request.getCurrentAddress(),
            request.getCurrentCity(),
            passwordEncryptor.encryptPassword(request.getPassword()),
            request.getEmail()
        );

        int id = userRepository.createUser(newUser);

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
