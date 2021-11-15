package server.controllers;

/**
 * An interface representing a connection to some implementation
 * of a back-end API.
 */
public interface IAPIGateway {

    /**
     * Create a new user and return a response in the form of a JSON string.
     *
     * @param firstName         the user's first name
     * @param lastName          the user's last name
     * @param currentAddress    the user's current address
     * @param currentCity       the user's current city
     * @param password          the user's password
     * @param email             the user's email
     */
    public String createUser(String firstName, String lastName, String currentAddress, String currentCity,
                             String password, String email);

    /**
     * Fetch a user by given user id.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId    the user's id
     * @return A JSON structure of the user
     */
    public String fetchUserAccount(boolean fromTerminal, String headerUserId, String userId);

    /**
     * Edit a user's account information.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId        the user's id
     * @param newFirstName  the user's new entered first name
     * @param newLastName   the user's new entered last name
     * @param newAddress    the user's new entered current address
     * @param newCity       the user's new entered current city
     * @param newPassword   the user's new entered password
     * @param newEmail      the user's new entered email
     * @return A JSON structure of the edited user's account information
     */
    public String editUserAccount(boolean fromTerminal, String headerUserId, String userId, String newFirstName, String newLastName,
                                  String newAddress, String newCity, String newPassword, String newEmail);

    /**
     * Fetch a user's profile by given user id.
     *
     * @param userId    the user's id
     * @return A JSON structure of the user's profile
     */
    public String fetchUserProfile(String userId);

    /**
     * Edit a user's profile information.
     *
     * @param fromTerminal      whether this action is being run from command line prompt
     * @param headerUserId      the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                          is true, this field does nothing.
     * @param userId            the user's id
     * @param newBiography      the user's new entered biography
     * @param newPhoneNumber    the user's new entered phone number
     * @param newInstagram      the user's new entered Instagram
     * @param newFacebook       the user's new entered Facebook
     * @return A JSON structure of the edited user's profile information
     */
    public String editUserProfile(boolean fromTerminal, String headerUserId, String userId, String newBiography, String newPhoneNumber,
                                  String newInstagram, String newFacebook);

    /**
     * Create a new pet.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId        the user id of the pet's owner
     * @param name          the pet's name
     * @param age           the pet's age
     * @param breed         the pet's breed
     * @param biography     the pet's biography
     * @return A JSON structure of the pet
     */
    public String createPet(boolean fromTerminal, String headerUserId, String userId, String name, int age, String breed, String biography);

    /**
     * Fetch a pet by given pet id.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId         the pet's id
     * @return A JSON structure of the pet
     */
    public String fetchPetProfile(boolean fromTerminal, String headerUserId, String petId);

    /**
     * Edit a pet's information.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId         the pet's id
     * @param newName       the pet's new name
     * @param newAge        the pet's new age
     * @param newBreed      the pet's new breed
     * @param newBiography  the pet's new biography
     * @return A JSON structure of the edited pet's information
     */
    public String editPet(boolean fromTerminal, String headerUserId, String petId, String newName, int newAge, String newBreed, String newBiography);

    /**
     * Generate a list of potential matches for a pet.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId
     * @return a String of "true" or "false whether the match successfully occurred
     */
    public String generatePotentialMatches(boolean fromTerminal, String headerUserId, int petId);

    /**
     * Add the pet with id pet2Id to the pet with id pet1Id's swiped list
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param pet1Id
     * @param pet2Id
     * @return a String of "true" or "false whether the match successfully occurred
     */
    public String swipePets(boolean fromTerminal, String headerUserId, int pet1Id, int pet2Id);

    /**
     * Remove the pet with id pet2Id to the pet with id pet1Id's swiped list
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param pet1Id        The id of the pet who is doing the unswiping
     * @param pet2Id        The id of the pet being unswiped
     * @return a String of "true" or "false whether the match successfully occurred
     */
    public String unswipePets(boolean fromTerminal, String headerUserId, int pet1Id, int pet2Id);

    /**
     * Add the pet with id pet2Id to the pet with id pet1Id's reject list
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param pet1Id        The id of the pet who is doing the rejecting
     * @param pet2Id        The id of the pet being rejected
     * @return a String of "true" or "false whether the rejection successfully occurred
     */
    public String rejectPets(boolean fromTerminal, String headerUserId, int pet1Id, int pet2Id);

    /**
     * Fetch all the pet ids of the pets that this pet has swiped on, returned in a JSON string
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId
     * @return a JSON string
     */
    public String fetchPetSwipes(boolean fromTerminal, String headerUserId, int petId);

    /**
     * Fetch all the pet ids of the pets that this pet has matched with, returned in a JSON string
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId
     * @return a JSON string
     */
    public String fetchPetMatches(boolean fromTerminal, String headerUserId, int petId);

    /**
     * Fetch all the pet ids of the pets that belong to the user with this userId, returned in a JSON string
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId
     * @return a JSON string
     */
    public String fetchUserPets(boolean fromTerminal, String headerUserId, int userId);

    /**
     * Generate a JWT for a user given their email and password for authentication.
     *
     * @param email
     * @param password
     * @return a JWT token as a String
     */
    public String generateJwt(String email, String password);

}