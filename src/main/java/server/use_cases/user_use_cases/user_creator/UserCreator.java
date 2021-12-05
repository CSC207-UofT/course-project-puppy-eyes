package server.use_cases.user_use_cases.user_creator;

import server.drivers.IGeocoderService;
import server.drivers.IPasswordEncryptor;
import server.drivers.LatLng;
import server.entities.User;
import server.entities.UserBuilder;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.ResponseModel;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidatorInputBoundary;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidatorRequestModel;

/**
 * A use case responsible for creating a new user.
 */
public class UserCreator implements UserCreatorInputBoundary {

    private final IUserRepository userRepository;
    private final IPasswordEncryptor passwordEncryptor;
    private final IGeocoderService geocoderService;
    private final UserAccountValidatorInputBoundary userAccountValidator;

    public UserCreator(IUserRepository userRepository, IPasswordEncryptor passwordEncryptor,
                       UserAccountValidatorInputBoundary userAccountValidator, IGeocoderService geocoderService) {
        this.userRepository = userRepository;
        this.passwordEncryptor = passwordEncryptor;
        this.userAccountValidator = userAccountValidator;
        this.geocoderService = geocoderService;
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

        // Ping geocoder API to calculate the lat lng
        // GeocoderService.getLatLng returns a List of LatLng objects, but here we are assuming that the current user's
        // current address and city are sufficiently specific to return a unique latitude-longitude tuple
        LatLng latLng = this.geocoderService.getLatLng(request.getCurrentAddress() + ", " + request.getCurrentCity()).get(0);

        User newUser = new UserBuilder(
                request.getFirstName(),
                request.getLastName(),
                passwordEncryptor.encryptPassword(request.getPassword()),
                request.getCurrentCity(),
                request.getEmail()
        )
        .currentAddress(request.getCurrentAddress())
        .lat(String.valueOf(latLng.getLat()))
        .lng(String.valueOf(latLng.getLng()))
        .create();

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
