package server.drivers.repository;

import org.springframework.stereotype.Repository;
import server.drivers.dbEntities.PetDatabaseEntity;
import server.use_cases.repo_abstracts.IPetRepository;

/**
 * An access point from the program to the "Pet" table in our database.
 */
@Repository
public class PetRepository implements IPetRepository {

    private final JpaPetRepository repository;

    public PetRepository(JpaPetRepository repository) {
        this.repository = repository;
    }

    /**
     * Create and save a new pet to the database.
     * @param name The pet's name.
     * @param age The pet's last name.
     *
     * @return The id of the new pet.
     */
    @Override
    public int createPet(String name, int age) {
        PetDatabaseEntity petDbEntity = new PetDatabaseEntity(name, age, "", "");
        repository.save(petDbEntity);

        return petDbEntity.getId();
    }


    // TODO: Implementing following methods

    @Override
    public void fetchPetProfile() {}

    @Override
    public void matchPet() {}

    @Override
    public void editPet() {}

}
