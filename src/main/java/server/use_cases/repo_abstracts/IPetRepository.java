package server.use_cases.repo_abstracts;

/**
 * An interface defining an access point from the program
 * to a repository storing the information of all pets.
 */
public interface IPetRepository {

    /**
     * Create a new pet and save it in the repository.
     *
     * @param name The pet's name.
     * @param age The Pet's age.
     * @param breed The Pet's breed.
     * @param biography The Pet's biography.
     * @return New pet's id.
     */
    public int createPet(String name, int age, String breed, String biography);

    /**
     * Fetch a pet's profile information given a pet id.
     *
     * @param petId The pet's id.
     * @return PetRepositoryPetProfileFetcherResponse
     * @throws PetNotFoundException
     */
    public PetRepositoryPetProfileFetcherResponse fetchPetProfile(int petId) throws PetNotFoundException;

    // TODO: Complete following methods (return, args,...)
    public void matchPet();
    public void editPet();
}
