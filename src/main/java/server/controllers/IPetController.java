package server.controllers;

/**
 * An interface representing a controller that handles all
 * functions relating to pet data.
 */
public interface IPetController {

    /**
     * Create a new pet.
     *
     * @param userId    the user id of this pet's owner
     * @param name      the pet's name
     * @param age       the pet's age
     * @param breed     the pet's breed
     * @param biography the pet's biography
     * @return A JSON structure
     */

    String createPet(String userId, String name, int age, String breed, String biography);

    /**
     * Fetch a pet by given id.
     *
     * @param petId     user entered pet id
     * @return A JSON structure
     */
    String fetchPetProfile(String petId);

    /**
     * Edit a pet.
     *
     * @param petId         user entered pet id
     * @param newName       user entered pet's new name
     * @param newAge        user entered pet's new age
     * @param newBreed      user entered pet's new breed
     * @param newBiography  user entered pet's new biography
     * @return A JSON structure
     */
    String editPet(String petId, String newName, int newAge, String newBreed, String newBiography);

    /**
     * Match two pets.
     *
     * @param pet1Id
     * @param pet2Id
     * @return
     */
    String matchPets(int pet1Id, int pet2Id);

    /**
     * Add the Pet with id pet2Id to the Pet with id pet1Id's swiped list.
     *
     * @param pet1Id
     * @param pet2Id
     * @return
     */
    String swipePets(int pet1Id, int pet2Id);

    /**
     * Remove the Pet with id pet2Id to the Pet with id pet1Id's swiped list.
     *
     * @param pet1Id
     * @param pet2Id
     * @return
     */
    String unswipePets(int pet1Id, int pet2Id);

}
