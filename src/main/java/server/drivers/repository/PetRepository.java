package server.drivers.repository;

import org.springframework.stereotype.Repository;
import server.drivers.dbEntities.PetDatabaseEntity;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.PetRepositoryPetProfileFetcherResponse;

import java.util.Optional;

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
     * @param age The pet's age.
     * @param breed The pet's breed.
     * @param biography The pet's biography.
     *
     * @return The id of the new pet.
     */
    @Override
    public int createPet(String name, int age, String breed, String biography) {
        PetDatabaseEntity petDbEntity = new PetDatabaseEntity(name, age, breed, biography);
        repository.save(petDbEntity);

        return petDbEntity.getId();
    }

    /**
     *
     * @param petId The pet's id.
     * @return An object containing the pet's name and age.
     * @throws PetNotFoundException if no pet with such an id was found.
     */
    @Override
    public PetRepositoryPetProfileFetcherResponse fetchPetProfile(int petId) throws PetNotFoundException {
        Optional<PetDatabaseEntity> searchResult = repository.findById(petId);

        if (searchResult.isPresent()) {
            PetDatabaseEntity pet = searchResult.get();
            return new PetRepositoryPetProfileFetcherResponse(pet.getName(), pet.getAge(), pet.getBreed(), pet.getBiography());
        } else {
            throw new PetNotFoundException("Pet of ID: " + petId + " not found");
        }

    }

    // TODO: Implementing following methods

    @Override
    public void matchPet() {}

    @Override
    public void editPet() {}

}
