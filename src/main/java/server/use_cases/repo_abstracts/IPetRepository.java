package server.use_cases.repo_abstracts;

/**
 * An interface defining an access point from the program
 * to a repository storing the information of all pets.
 */
public interface IPetRepository {

    /**
     * Create a new pet and save it in the repository.
     *
     * @param userId the user id of the pet's owner
     * @param name the pet's name;
     * @param age the pet's age;
     * @param breed the pet's breed;
     * @param biography the Pet's biography;
     * @return new pet's id;
     */
    public int createPet(int userId, String name, int age, String breed, String biography);

    /**
     * Fetch a pet's profile information by a given pet id.
     *
     * @param petId the pet's id;
     * @return PetRepositoryPetProfileFetcherResponse;
     * @throws PetNotFoundException;
     */
    public PetRepositoryPetProfileFetcherResponse fetchPetProfile(int petId) throws PetNotFoundException;

    /**
     * Edit a pet's information, given all new information.
     *
     * @param petId the pet's id;
     * @param newName the pet's new name;
     * @param newAge the pet's new age;
     * @param newBreed the pet's new breed;
     * @param newBiography the pet's new biography;
     * @return if the editing is successfully done;
     */
    public boolean editPet(int petId, String newName, int newAge, String newBreed, String newBiography);
  
}