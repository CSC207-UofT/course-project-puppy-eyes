package server.controllers;

import java.util.List;

/**
 * An interface representing a controller that handles all
 * functions relating to pet data.
 */
public interface IPetController {

    /**
     * Create a new pet.
     *
     * @param userId The user id of this pet's owner
     * @param name The pet's name;
     * @param age The pet's age;
     * @param breed The pet's breed;
     * @param biography The pet's biography;
     * @return A JSON structure;
     */

    String createPet(int userId, String name, int age, String breed, String biography);

    /**
     * Fetch a pet by given id.
     *
     * @param petId User entered pet id;
     * @return A JSON structure
     */
    String fetchPetProfile(String petId);

    /**
     * Edit a pet.
     *
     * @param petId User entered pet id;
     * @param newName User entered pet's new name;
     * @param newAge User entered pet's new age;
     * @param newBreed User entered pet's new breed;
     * @param newBiography User entered pet's new biography;
     * @return A JSON structure;
     */
    String editPet(String petId, String newName, int newAge, String newBreed, String newBiography);

    /**
     * Add the Pet with id pet2Id to the Pet with id pet1Id's rejection list
     * @param pet1Id
     * @param pet2Id
     * @return a String of "true" or "false whether the rejection successfully occurred
     */
    String rejectPets(int pet1Id, int pet2Id);

    /**
     * Add the Pet with id pet2Id to the Pet with id pet1Id's swiped list
     * @param pet1Id
     * @param pet2Id
     * @return a String of "true" or "false whether the swipe successfully occurred
     */
    String swipePets(int pet1Id, int pet2Id);

    /**
     * Remove the Pet with id pet2Id to the Pet with id pet1Id's swiped list
     * @param pet1Id
     * @param pet2Id
     * @return a String of "true" or "false whether the unswipe successfully occurred
     */
    String unswipePets(int pet1Id, int pet2Id);

    /**
     * Return a list of pet ids that the given pet has swiped on
     * @param petId
     * @return a JSON structure
     */
    String fetchPetSwipes(int petId);

    /**
     * Return a list of pet ids that the given pet has matched with
     * @param petId
     * @return a JSON structure
     */
    String fetchPetMatches(int petId);

}
