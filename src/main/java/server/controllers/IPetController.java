package server.controllers;

import server.use_cases.ResponseModel;

/**
 * An interface representing a controller that handles all
 * functions relating to pet data.
 */
public interface IPetController {

    /**
     * Create a new pet.
     *
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId    the user id of this pet's owner
     * @param name      the pet's name
     * @param age       the pet's age
     * @param breed     the pet's breed
     * @param biography the pet's biography
     * @return A ResponseModel containing the response data
     */

    ResponseModel createPet(boolean fromTerminal, String headerUserId, String userId, String name, String age, String breed, String biography);

    /**
     * Fetch a pet by given id.
     *
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId     user entered pet id
     * @return A ResponseModel containing the response data
     */
    ResponseModel fetchPetProfile(boolean fromTerminal, String headerUserId, String petId);

    /**
     * Edit a pet.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId         user entered pet id
     * @param newName       user entered pet's new name
     * @param newAge        user entered pet's new age
     * @param newBreed      user entered pet's new breed
     * @param newBiography  user entered pet's new biography
     * @return A ResponseModel containing the response data
     */
    ResponseModel editPet(boolean fromTerminal, String headerUserId, String petId, String newName, String newAge, String newBreed, String newBiography);

    /**
     * Add the Pet with id pet2Id to the Pet with id pet1Id's rejection list
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param pet1Id
     * @param pet2Id
     * @return A ResponseModel containing the response data
     */
    ResponseModel rejectPets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id);

    /**
     * Return a list of pet ids that the pet may potentially want to match with
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId
     * @return a JSON structure
     */
    ResponseModel generatePotentialMatches(boolean fromTerminal, String headerUserId, String petId);

    /**
     * Add the Pet with id pet2Id to the Pet with id pet1Id's swiped list.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param pet1Id
     * @param pet2Id
     * @return A ResponseModel containing the response data
     */
    ResponseModel swipePets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id);

    /**
     * Remove the Pet with id pet2Id to the Pet with id pet1Id's swiped list.
     *
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param pet1Id
     * @param pet2Id
     * @return A ResponseModel containing the response data
     */
    ResponseModel unswipePets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id);

    /**
     * Return a list of pet ids that the given pet has swiped on
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId
     * @return A ResponseModel containing the response data
     */
    ResponseModel fetchPetSwipes(boolean fromTerminal, String headerUserId, String petId);

    /**
     * Return a list of pet ids that the given pet has matched with
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId
     * @return A ResponseModel containing the response data
     */
    ResponseModel fetchPetMatches(boolean fromTerminal, String headerUserId, String petId);

    /**
     * Set's a pet's profile picture to the image represented by the Base64 encoding.
     * @param headerUserId
     * @param petId
     * @param base64Encoded
     * @return a ResponseModel containing the response data
     */
    ResponseModel setPetProfile(String headerUserId, String petId, String base64Encoded);

    /**
     * Remove the pet with pet2Id to the pet with pet1Id's swiped list
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param pet1Id
     * @param pet2Id
     * @return a ResponseModel containing the response data
     */
    ResponseModel unmatchPets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id);

    /**
     * Return a URL containing this pet's profile image
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId         the pet's id
     * @return a ResponseModel containing the response data
     */
    ResponseModel fetchPetProfileImage(boolean fromTerminal, String headerUserId, String petId);

    /**
     * Return a list of URL containing this pet's gallery images
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId         the pet's id
     * @return a ResponseModel containing the response data
     */
    ResponseModel fetchPetGalleryImages(boolean fromTerminal, String headerUserId, String petId);

}
