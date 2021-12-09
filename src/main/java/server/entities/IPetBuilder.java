package server.entities;

/**
 * An interface that defines the methods any PetBuilder class must implement.
 */
public interface IPetBuilder {

    /**
     * Create a Pet object using the builder's fields
     *
     * @return a new User object
     */
    Pet create();

    /**
     * Clear all the builder's fields
     *
     * @return the current instance to allow chaining
     */
    IPetBuilder reset();

    /**
     * Set the id field
     *
     * @param id the id of the new pet
     * @return the current instance to allow chaining
     */
    IPetBuilder id(int id);

    /**
     * Set the biography field
     *
     * @param biography the biography of the new pet
     * @return the current instance to allow chaining
     */
    IPetBuilder biography(String biography);


}
