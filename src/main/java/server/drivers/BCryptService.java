package server.drivers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * Implement a password encryptor using BCrypt.
 */
@Component
public class BCryptService implements IPasswordEncryptor {

    @Override
    public String encryptPassword(String rawPassword) {
        return BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray());
    }

    @Override
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return BCrypt.verifyer().verify(rawPassword.toCharArray(), hashedPassword.toCharArray()).verified;
    }
}
