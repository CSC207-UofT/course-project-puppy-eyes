package server.drivers;

import java.util.Date;

/**
 * An interface that defines the methods that a JWT service supplies.
 */
public interface IJwtService {

    /**
     * Extract the subject from the JWT token
     *
     * @param token the JWT token
     * @return the subject as a String
     */
    String extractSubject(String token);

    /**
     * Return the expiration of the JWT token
     *
     * @param token the JWT token
     * @return the expiration as a Date object
     */
    Date extractExpiration(String token);

    /**
     * Create a new JWT token with the given subject
     *
     * @param subject the given subject
     * @return a JWT token as a String
     */
    String createToken(String subject);

    /**
     * Return whether the given token and its subject is valid
     *
     * @param token   the given token
     * @param subject the given subject
     * @return true if valid, else false
     */
    boolean validateToken(String token, String subject);

}
