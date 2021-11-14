package server.use_cases;

import server.drivers.JwtService;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.ResponseModel;

/**
 * A use case responsible for generating a JWT token for a given user
 */
public class SessionTokenGenerator implements SessionTokenGeneratorInputBoundary {

    private final IUserRepository userRepository;
    private final JwtService jwtService;

    public SessionTokenGenerator(IUserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseModel generateSessionToken(SessionTokenGeneratorRequestModel request) {
        if (!userRepository.validateCredentials(request.getEmail(), request.getPassword())) {
            return new ResponseModel(false, "Incorrect credentials.");
        }

        String token = jwtService.createToken(String.valueOf(userRepository.fetchIdFromEmail(request.getEmail())));
        return new ResponseModel(
            true,
            "Successfuly generated session token.",
            new SessionTokenGeneratorResponseModel(token)
        );
    }
}
