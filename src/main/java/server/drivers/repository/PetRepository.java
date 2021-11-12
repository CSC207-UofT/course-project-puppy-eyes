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
     *
     * @param name the pet's name;
     * @param age the pet's age;
     * @param breed the pet's breed;
     * @param biography the pet's biography;
     *
     * @return the id of the new pet;
     */
    @Override
    public int createPet(int userId, String name, int age, String breed, String biography) {
        PetDatabaseEntity petDbEntity = new PetDatabaseEntity(userId, name, age, breed, biography);
        repository.save(petDbEntity);

        return petDbEntity.getId();
    }

    /**
     * Fetch a pet in the database by given pet id.
     *
     * @param petId the pet's id;
     * @return an object containing the pet's name and age;
     * @throws PetNotFoundException if no pet with such an id was found;
     */
    @Override
    public PetRepositoryPetProfileFetcherResponse fetchPetProfile(int petId) throws PetNotFoundException {
        Optional<PetDatabaseEntity> searchResult = repository.findById(petId);

        if (searchResult.isPresent()) {
            PetDatabaseEntity pet = searchResult.get();
            return new PetRepositoryPetProfileFetcherResponse(pet.getName(), pet.getAge(), pet.getBreed(),
                    pet.getBiography());
        } else {
            throw new PetNotFoundException("Pet of ID: " + petId + " not found");
        }

    }

    /**
     * Edit a pet's information in the database.
     *
     * @param petId the pet's id;
     * @param newName the pet's new name;
     * @param newAge the pet's new age;
     * @param newBreed the pet's new breed;
     * @param newBiography the pet's new biography;
     * @return if editing is successfully done;
     */
    @Override
    public boolean editPet(int petId, String newName, int newAge, String newBreed, String newBiography) {
        Optional<PetDatabaseEntity> searchResult = repository.findById(petId);

        if (searchResult.isPresent()) {
            PetDatabaseEntity pet = searchResult.get();
            pet.setName(newName);
            pet.setAge(newAge);
            pet.setBreed(newBreed);
            pet.setBiography(newBiography);
            repository.save(pet);
            return true;
        } else {
            return false;
        }
    }

    // TODO: Implement following method
    @Override
    public void matchPet() {}
}
