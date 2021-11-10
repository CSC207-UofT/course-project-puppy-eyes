package server.drivers.repository;

import server.drivers.dbEntities.PetDatabaseEntity;
import server.use_cases.repo_abstracts.IPetRepository;

public class PetRepository implements IPetRepository {

    private final JpaPetRepository repository;

    public PetRepository(JpaPetRepository repository) {
        this.repository = repository;
    }

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
