package server.use_cases.repo_abstracts;

public interface IPetRepository {

    /**
     * Create a new Pet and save it in the repository.
     *
     * @param userId The user id of this pet's owner
     * @param name The pet's name.
     * @param age The Pet's age.
     * @return New pet's id.
     */
    public int createPet(int userId, String name, int age);

    // TODO: Complete following methods (return, args,...)
    public void fetchPetProfile();

    public void editPet();

}
