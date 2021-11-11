package server.controllers;

/**
 * An interface representing a controller that handles all
 * functions relating to pet data.
 */
public interface IPetController {

    /**
     * Create a new pet.
     *
     * @param name The pet's name;
     * @param age The pet's age;
     * @param breed The pet's breed;
     * @param biography The pet's biography;
     * @return A JSON structure;
     */
    String createPet(String name, int age, String breed, String biography);

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

    // TODO: following methods is not completed yet
    String matchPet();

    }
