package server.use_cases;

import server.drivers.JwtService;
import server.use_cases.repo_abstracts.IUserRepository;

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
    public SessionTokenGeneratorResponseModel generateSessionToken(SessionTokenGeneratorRequestModel request) {
        String token = null;
        boolean success = false;

        if (userRepository.validateCredentials(request.getEmail(), request.getPassword())) {
            token = jwtService.createToken(String.valueOf(userRepository.fetchIdFromEmail(request.getEmail())));
            success = true;
        }

        SessionTokenGeneratorResponseModel response = new SessionTokenGeneratorResponseModel(token, success);
        return response;
    }
}
