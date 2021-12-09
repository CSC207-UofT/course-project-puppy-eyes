package server.use_cases.repo_abstracts;

import server.entities.Pet;

import java.util.List;

/**
 * An interface defining an access point from the program
 * to a repository storing the information of all pets.
 */
public interface IPetRepository {

    /**
     * Create and save a new pet to the database.
     *
     * @param userId    the pet's user's id
     * @param name      the pet's name
     * @param age       the pet's age
     * @param breed     the pet's breed
     * @param biography the pet's biography
     * @return the id of the new pet;
     */
    int createPet(int userId, String name, int age, String breed, String biography);

    /**
     * Fetch a Pet from the database.
     *
     * @param petId the id of the pet to be fetched from the database
     * @return the Pet object
     */
    Pet fetchPet(int petId);

    /**
     * Edit a pet's information, given all new information.
     *
     * @param petId        the pet's id
     * @param newName      the pet's new name
     * @param newAge       the pet's new age
     * @param newBreed     the pet's new breed
     * @param newBiography the pet's new biography
     * @return if the editing is successfully done
     */
    boolean editPet(int petId, String newName, int newAge, String newBreed, String newBiography);

    /**
     * Add pet2 to pet1's swiped list
     *
     * @param pet1Id
     * @param pet2Id
     * @return whether the swipe was successful
     */
    boolean swipePets(int pet1Id, int pet2Id);

    /**
     * Add pet1 and pet2 to each other's matched list.
     *
     * @param pet1Id
     * @param pet2Id
     * @return whether the swipe was successful
     */
    boolean matchPets(int pet1Id, int pet2Id);

    /**
     * Remove pet2 from pet1's swiped list
     *
     * @param pet1Id
     * @param pet2Id
     * @return whether the swipe was successful
     */
    boolean unswipePets(int pet1Id, int pet2Id);

    /**
     * Add pet2 to pet1's rejected list
     *
     * @param pet1Id
     * @param pet2Id
     * @return whether the rejection was successful
     */
    boolean rejectPets(int pet1Id, int pet2Id);

    /**
     * Remove pet1 and pet2 from each other's matched list.
     *
     * @param pet1Id
     * @param pet2Id
     * @return whether the rejection was successful
     */
    boolean unmatchPets(int pet1Id, int pet2Id);

    /**
     * Return a list of pet ids that the given pet has swiped on
     *
     * @param petId
     * @return a list of pet ids that the given pet has swiped on
     */
    List<Integer> fetchSwipedOn(int petId);

    /**
     * Return a list of pet ids that the given pet has rejected
     *
     * @param petId
     * @return a list of pet ids that the given pet has rejected
     */
    List<Integer> fetchRejected(int petId);

    /**
     * Return a list of pet ids that the given pet has matched with
     *
     * @param petId
     * @return a list of pet ids that the given pet has matched with
     */
    List<Integer> fetchMatches(int petId);

}