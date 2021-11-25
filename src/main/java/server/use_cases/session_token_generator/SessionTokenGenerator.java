package server.use_cases.session_token_generator;

import server.drivers.IJwtService;
import server.drivers.IPasswordEncryptor;
import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.ResponseModel;
import server.use_cases.repo_abstracts.UserNotFoundException;

/**
 * A use case responsible for generating a JWT token for a given user
 */
public class SessionTokenGenerator implements SessionTokenGeneratorInputBoundary {

    private final IUserRepository userRepository;
    private final IJwtService jwtService;
    private final IPasswordEncryptor passwordEncryptor;

    public SessionTokenGenerator(IUserRepository userRepository, IJwtService jwtService,
                                 IPasswordEncryptor passwordEncryptor) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public ResponseModel generateSessionToken(SessionTokenGeneratorRequestModel request) {
        User user;

        try {
            user = userRepository.fetchUser(userRepository.fetchIdFromEmail(request.getEmail()));
        } catch (UserNotFoundException exception) {
            return new ResponseModel(false, "No account with this email exists.");
        }

        // Check credentials
        if (!passwordEncryptor.verifyPassword(request.getPassword(), user.getPasswordHash())) {
            return new ResponseModel(false, "Incorrect credentials.");
        }

        String token = jwtService.createToken(String.valueOf(userRepository.fetchIdFromEmail(request.getEmail())));
        return new ResponseModel(
            true,
            "Successfully generated session token.",
            new SessionTokenGeneratorResponseModel(token)
        );
    }
}
