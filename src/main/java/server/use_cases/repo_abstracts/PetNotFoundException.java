package server.use_cases.repo_abstracts;

/**
 * An Exception which is thrown, when a pet is attempted to be fetched,
 * but is not found in the repository.
 */
public class PetNotFoundException extends Exception {

    public int id;

    public PetNotFoundException(String message, int id) {
        super(message);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
