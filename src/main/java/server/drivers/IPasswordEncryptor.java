package server.drivers;

/**
 * An interface defining a method to encrypt a password.
 */
public interface IPasswordEncryptor {

    /**
     * Return an encrypted version of a raw password.
     *
     * @param rawPassword the raw password
     * @return an encrypted password as a String
     */
    String encryptPassword(String rawPassword);

    /**
     * Return whether the raw password matches the hashed password.
     *
     * @param rawPassword    the raw password
     * @param hashedPassword the hashed password
     * @return true if matched, else false
     */
    boolean verifyPassword(String rawPassword, String hashedPassword);

}
