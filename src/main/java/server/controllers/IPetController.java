package server.controllers;

/**
 * An interface representing a controller that handles all
 * functions relating to pet data.
 */
public interface IPetController {

    /**
     * Creating a new pet.
     *
     * @param userId The user id of this pet's owner
     * @param name The pet's name;
     * @param age The pet's age;
     * @return A response in the form of a JSON string
     */
    String createPet(int userId, String name, int age);

    // TODO: following methods is not completed yet

    String fetchPetProfile();

    /**
     * Match two pets.
     * @param pet1Id
     * @param pet2Id
     * @return
     */
    String matchPets(int pet1Id, int pet2Id);

    /**
     * Add the Pet with id pet2Id to the Pet with id pet1Id's swiped list
     * @param pet1Id
     * @param pet2Id
     * @return
     */
    String swipePets(int pet1Id, int pet2Id);

    /**
     * Remove the Pet with id pet2Id to the Pet with id pet1Id's swiped list
     * @param pet1Id
     * @param pet2Id
     * @return
     */
    String unswipePets(int pet1Id, int pet2Id);

    String editPet();

}
