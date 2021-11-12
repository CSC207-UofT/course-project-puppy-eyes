package server.drivers.repository;

import org.springframework.stereotype.Repository;
import server.drivers.dbEntities.PetDatabaseEntity;
import server.use_cases.repo_abstracts.IPetRepository;

import java.util.Optional;

@Repository
public class PetRepository implements IPetRepository {

    private final JpaPetRepository repository;

    public PetRepository(JpaPetRepository repository) {
        this.repository = repository;
    }

    @Override
    public int createPet(int userId, String name, int age) {
        PetDatabaseEntity petDbEntity = new PetDatabaseEntity(userId, name, age, "", "");
        repository.save(petDbEntity);

        return petDbEntity.getId();
    }


    // TODO: Implementing following methods

    @Override
    public void fetchPetProfile() {}

    @Override
    public void editPet() {}

}
