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
    public User create();

    /**
     * Clear all of the builder's fields
     *
     * @return the current instance to allow chaining
     */
    public IUserBuilder reset();

    /**
     * Set the id field
     *
     * @param id
     * @return the current instance to allow chaining
     */
    public IUserBuilder id(int id);

    /**
     * Set the biography field
     *
     * @param biography
     * @return the current instance to allow chaining
     */
    public IUserBuilder biography(String biography);

    /**
     * Set the currentAddress field
     *
     * @param currentAddress
     * @return the current instance to allow chaining
     */
    public IUserBuilder currentAddress(String currentAddress);

    /**
     * Set the phoneNumber field
     *
     * @param phoneNumber
     * @return the current instance to allow chaining
     */
    public IUserBuilder phoneNumber(String phoneNumber);

    /**
     * Set the instagram field
     *
     * @param instagram
     * @return the current instance to allow chaining
     */
    public IUserBuilder instagram(String instagram);

    /**
     * Set the facebook field
     *
     * @param facebook
     * @return the current instance to allow chaining
     */
    public IUserBuilder facebook(String facebook);

    /**
     * Set the lat field
     *
     * @param lat
     * @return the current instance to allow chaining
     */
    public IUserBuilder lat(String lat);

    /**
     * Set the lng field
     *
     * @param lng
     * @return the current instance to allow chaining
     */
    public IUserBuilder lng(String lng);

}