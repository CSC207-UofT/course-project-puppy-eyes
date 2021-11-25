package server.use_cases.repo_abstracts;

import server.entities.Pet;

import java.util.List;

/**
 * An interface defining an access point from the program
 * to a repository storing the information of all pets.
 */
public interface IPetRepository {

    /**
     * Create a new pet and save it in the repository.
     *
     * @param userId    the user id of the pet's owner
     * @param name      the pet's name
     * @param age       the pet's age
     * @param breed     the pet's breed
     * @param biography the Pet's biography
     *
     * @return new pet's id
     */
    public int createPet(int userId, String name, int age, String breed, String biography);

    /**
     * Fetch a Pet from the database.
     * @param petId
     * @return
     * @throws PetNotFoundException
     */
    public Pet fetchPet(int petId) throws PetNotFoundException;

    /**
     * Edit a pet's information, given all new information.
     *
     * @param petId         the pet's id
     * @param newName       the pet's new name
     * @param newAge        the pet's new age
     * @param newBreed      the pet's new breed
     * @param newBiography  the pet's new biography
     *
     * @return if the editing is successfully done
     */
    public boolean editPet(int petId, String newName, int newAge, String newBreed, String newBiography);

    /**
     * Add pet2 to pet1's swiped list
     * @param pet1Id
     * @param pet2Id
     * @return whether the swipe was successful
     */
    public boolean swipePets(int pet1Id, int pet2Id) throws PetNotFoundException;

    /**
     * Add pet1 and pet2 to each other's matched list.
     * @param pet1Id
     * @param pet2Id
     * @return whether the swipe was successful
     */
    public boolean matchPets(int pet1Id, int pet2Id) throws PetNotFoundException;

    /**
     * Remove pet2 from pet1's swiped list
     * @param pet1Id
     * @param pet2Id
     * @return whether the swipe was successful
     */
    public boolean unswipePets(int pet1Id, int pet2Id) throws PetNotFoundException;

    /**
     * Add pet2 to pet1's rejected list
     * @param pet1Id
     * @param pet2Id
     * @return whether the rejection was successful
     */
    public boolean rejectPets(int pet1Id, int pet2Id) throws PetNotFoundException;

    /**
     * Return a list of pet ids that the given pet has swiped on
     * @param petId
     * @return a list of pet ids that the given pet has swiped on
     */
    public List<Integer> fetchSwipedOn(int petId) throws PetNotFoundException;

    /**
     * Return a list of pet ids that the given pet has rejected
     * @param petId
     * @return a list of pet ids that the given pet has rejected
     */
    public List<Integer> fetchRejected(int petId) throws PetNotFoundException;

    /**
     * Return a list of pet ids that the given pet has matched with
     * @param petId
     * @return a list of pet ids that the given pet has matched with
     */
    public List<Integer> fetchMatches(int petId) throws PetNotFoundException;

}