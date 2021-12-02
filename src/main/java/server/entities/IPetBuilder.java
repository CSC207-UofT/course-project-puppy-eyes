package server.entities;

/**
 * An interface that defines the methods any PetBuilder class must implement.
 */
public interface IPetBuilder {

    /**
     * Create a Pet object using the builder's fields
     * @return a new User object
     */
    public Pet create();

    /**
     * Clear all of the builder's fields
     * @return the current instance to allow chaining
     */
    public IPetBuilder reset();

    /**
     * Set the id field
     * @param id
     * @return the current instance to allow chaining
     */
    public IPetBuilder id(int id);

    /**
     * Set the biography field
     * @param biography
     * @return the current instance to allow chaining
     */
    public IPetBuilder biography(String biography);


}
