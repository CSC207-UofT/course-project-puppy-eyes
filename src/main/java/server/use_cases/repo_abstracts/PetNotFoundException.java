package server.use_cases.repo_abstracts;

/**
 * An Exception which is thrown, when a pet is attempted to be fetched,
 * but is not found in the repository.
 */
public class PetNotFoundException extends Exception {
    public PetNotFoundException(String message) {
        super(message);
    }
}
