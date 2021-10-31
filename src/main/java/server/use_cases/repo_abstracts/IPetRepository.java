package server.use_cases.repo_abstracts;

public interface IPetRepository {

    /**
     * Create a new Pet and save it in the repository.
     *
     * @param name The pet's name.
     * @param age The Pet's age.
     * @return New pet's id.
     */
    public int createPet(String name, int age);


    // TODO: Complete following methods (return, args,...)
    public void fetchPetProfile();
    public void matchPet();
    public void editPet();

}
