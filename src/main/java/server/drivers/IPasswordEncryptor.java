package server.drivers;

/**
 * An interface defining a method to encrypt a password.
 */
public interface IPasswordEncryptor {

    /**
     * Return an encrypted version of a raw password.
     * @param rawPassword
     * @return an encrypted password as a String
     */
    public String encryptPassword(String rawPassword);

    /**
     * Return whether the raw password matches the hashed password.
     * @param rawPassword
     * @param hashedPassword
     * @return true if matched, else false
     */
    public boolean verifyPassword(String rawPassword, String hashedPassword);

}
