package server.entities;

/**
 * An interface that defines the methods any UserBuilder class must implement.
 */
public interface IUserBuilder {

    /**
     * Create a User object using the builder's fields
     *
     * @return a new User object
     */
    User create();

    /**
     * Clear all of the builder's fields
     *
     * @return the current instance to allow chaining
     */
    IUserBuilder reset();

    /**
     * Set the id field
     *
     * @param id the id of the new user
     * @return the current instance to allow chaining
     */
    IUserBuilder id(int id);

    /**
     * Set the biography field
     *
     * @param biography the biography of the new user
     * @return the current instance to allow chaining
     */
    IUserBuilder biography(String biography);

    /**
     * Set the currentAddress field
     *
     * @param currentAddress the current address of the new user
     * @return the current instance to allow chaining
     */
    IUserBuilder currentAddress(String currentAddress);

    /**
     * Set the phoneNumber field
     *
     * @param phoneNumber the phone number of the new user
     * @return the current instance to allow chaining
     */
    IUserBuilder phoneNumber(String phoneNumber);

    /**
     * Set the instagram field
     *
     * @param instagram the instagram handle of the new user
     * @return the current instance to allow chaining
     */
    IUserBuilder instagram(String instagram);

    /**
     * Set the facebook field
     *
     * @param facebook the facebook name of the new user
     * @return the current instance to allow chaining
     */
    IUserBuilder facebook(String facebook);

    /**
     * Set the lat field
     *
     * @param lat the latitude of the new user's location
     * @return the current instance to allow chaining
     */
    IUserBuilder lat(String lat);

    /**
     * Set the lng field
     *
     * @param lng the longitude of the new user's location
     * @return the current instance to allow chaining
     */
    IUserBuilder lng(String lng);

}