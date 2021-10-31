package server.controllers;

/**
 * An interface representing a controller that handles all
 * functions relating to pet data.
 */
public interface IPetController {

    /**
     * Creating a new pet.
     *
     * @param name The pet's name;
     * @param age The pet's age;
     * @return A response in the form of a JSON string
     */
    String createPet(String name, int age);

    // TODO: following methods is not completed yet

    String fetchPetProfile();

    String matchPet();

    String editPet();

}
