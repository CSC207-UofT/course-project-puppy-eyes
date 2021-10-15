package server.use_cases.repo_abstracts;

/**
 * An Exception which is thrown, when a user is attempted to be fetched,
 * but is not found in the repository.
 */
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }
}
