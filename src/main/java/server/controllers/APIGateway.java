package server.controllers;

/**
 * An interface representing a connection to some implementation
 * of a back-end API.
 */
public interface APIGateway {

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
     * @param userId    the user's id
     * @return A JSON structure of the user
     */
    public String fetchUserAccount(String userId);

    /**
     * Edit a user's account information.
     *
     * @param userId        the user's id
     * @param newFirstName  the user's new entered first name
     * @param newLastName   the user's new entered last name
     * @param newAddress    the user's new entered current address
     * @param newCity       the user's new entered current city
     * @param newPassword   the user's new entered password
     * @param newEmail      the user's new entered email
     * @return A JSON structure of the edited user's account information
     */
    public String editUserAccount(String userId, String newFirstName, String newLastName, String newAddress,
                                  String newCity, String newPassword, String newEmail);

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
     * @param userId            the user's id
     * @param newBiography      the user's new entered biography
     * @param newPhoneNumber    the user's new entered phone number
     * @param newInstagram      the user's new entered Instagram
     * @param newFacebook       the user's new entered Facebook
     * @return A JSON structure of the edited user's profile information
     */
    public String editUserProfile(String userId, String newBiography, String newPhoneNumber,
                                  String newInstagram, String newFacebook);

    /**
     * Create a new pet.
     *
     * @param userId    the user id of the pet's owner
     * @param name      the pet's name
     * @param age       the pet's age
     * @param breed     the pet's breed
     * @param biography the pet's biography
     * @return A JSON structure of the pet
     */
    public String createPet(String userId, String name, int age, String breed, String biography);

    /**
     * Fetch a pet by given pet id.
     *
     * @param petId     the pet's id
     * @return A JSON structure of the pet
     */
    public String fetchPetProfile(String petId);

    /**
     * Edit a pet's information.
     *
     * @param petId         the pet's id
     * @param newName       the pet's new name
     * @param newAge        the pet's new age
     * @param newBreed      the pet's new breed
     * @param newBiography  the pet's new biography
     * @return A JSON structure of the edited pet's information
     */
    public String editPet(String petId, String newName, int newAge, String newBreed, String newBiography);

    /**
     * Add the pet with id pet2Id to the pet with id pet1Id's swiped list
     * @param pet1Id
     * @param pet2Id
     * @return a String of "true" or "false whether the match successfully occurred
     */
    public String swipePets(int pet1Id, int pet2Id);

    /**
     * Remove the pet with id pet2Id to the pet with id pet1Id's swiped list
     * @param pet1Id
     * @param pet2Id
     * @return a String of "true" or "false whether the match successfully occurred
     */
    public String unswipePets(int pet1Id, int pet2Id);

    /**
     * Add the pet with id pet2Id to the pet with id pet1Id's reject list
     * @param pet1Id
     * @param pet2Id
     * @return a String of "true" or "false whether the rejection successfully occurred
     */
    public String rejectPets(int pet1Id, int pet2Id);

    /**
     * Fetch all the pet ids of the pets that this pet has swiped on, returned in a JSON string
     * @param petId
     * @return a JSON string
     */
    public String fetchPetSwipes(int petId);

    /**
     * Fetch all the pet ids of the pets that this pet has matched with, returned in a JSON string
     * @param petId
     * @return a JSON string
     */
    public String fetchPetMatches(int petId);

    /**
     * Fetch all the pet ids of the pets that belong to the user with this userId, returned in a JSON string
     * @param userId
     * @return a JSON string
     */
    public String fetchUserPets(int userId);

}