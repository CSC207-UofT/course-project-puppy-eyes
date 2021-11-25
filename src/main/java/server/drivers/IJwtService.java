package server.drivers;

import java.util.Date;

/**
 * An interface that defines the methods that a JWT service supplies.
 */
public interface IJwtService {

    /**
     * Extract the subject from the JWT token
     * @param token
     * @return the subject as a String
     */
    public String extractSubject(String token);

    /**
     * Return the expiration of the JWT token
     * @param token
     * @return the expiration as a Date object
     */
    public Date extractExpiration(String token);

    /**
     * Create a new JWT token with the given subject
     * @param subject
     * @return a JWT token as a String
     */
    public String createToken(String subject);

    /**
     * Return whether the given token and its subject is valid
     * @param token
     * @param subject
     * @return true if valid, els efalse
     */
    public boolean validateToken(String token, String subject);

}
