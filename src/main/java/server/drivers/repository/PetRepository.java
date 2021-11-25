package server.drivers.repository;

import org.springframework.stereotype.Component;
import server.drivers.dbEntities.PetDatabaseEntity;
import server.drivers.dbEntities.RelationDatabaseEntity;
import server.entities.Pet;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * An access point from the program to the "Pet" table in our database.
 */
@Component
public class PetRepository implements IPetRepository {

    private final JpaPetRepository repository;
    private final RelationRepository relationRepository;

    public PetRepository(JpaPetRepository repository, RelationRepository relationRepository) {
        this.repository = repository;
        this.relationRepository = relationRepository;
    }

    /**
     * Create and save a new pet to the database.
     *
     * @param name      the pet's name
     * @param age       the pet's age
     * @param breed     the pet's breed
     * @param biography the pet's biography
     *
     * @return the id of the new pet;
     */
    @Override
    public int createPet(int userId, String name, int age, String breed, String biography) {
        PetDatabaseEntity petDbEntity = new PetDatabaseEntity(userId, name, age, breed, biography);
        repository.save(petDbEntity);

        return petDbEntity.getId();
    }

    @Override
    public Pet fetchPet(int petId) throws PetNotFoundException {
        Optional<PetDatabaseEntity> searchResult = repository.findById(petId);

        // Todo factory to create pet from db pet
        if (searchResult.isPresent()) {
            PetDatabaseEntity dbPet = searchResult.get();
            Pet pet = new Pet(dbPet.getUser().getId(), dbPet.getName(), dbPet.getAge(), dbPet.getBreed(), dbPet.getBiography()) {};
            pet.setId(dbPet.getId());
            return pet;
        } else {
            throw new PetNotFoundException("Pet of ID: " + petId + " not found", petId);
        }
    }

    /**
     * Edit a pet's information in the database.
     *
     * @param petId         the pet's id
     * @param newName       the pet's new name
     * @param newAge        the pet's new age
     * @param newBreed      the pet's new breed
     * @param newBiography  the pet's new biography
     * @return if editing is successfully done
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

    // TODO fix this not working
    /**
     * Add pet2 to pet1's swiped list
     * @param pet1Id
     * @param pet2Id
     * @return whether the swipe was successful
     */
    @Override
    public boolean swipePets(int pet1Id, int pet2Id) throws PetNotFoundException {
        if (!repository.existsById(pet1Id)) {
            throw new PetNotFoundException("Pet of ID: " + pet1Id + " not found", pet1Id);
        }

        if (!repository.existsById(pet2Id)) {
            throw new PetNotFoundException("Pet of ID: " + pet2Id + " not found", pet2Id);
        }

        RelationDatabaseEntity matchRelation = new RelationDatabaseEntity(pet1Id, pet2Id, "SWIPE");
        relationRepository.getRepository().save(matchRelation);
        return true;
    }

    /**
     * Add pet1 and pet2 to each other's matched list.
     * @param pet1Id
     * @param pet2Id
     * @return whether the swipe was successful
     */
    @Override
    public boolean matchPets(int pet1Id, int pet2Id) throws PetNotFoundException {
        if (!repository.existsById(pet1Id)) {
            throw new PetNotFoundException("Pet of ID: " + pet1Id + " not found", pet1Id);
        }

        if (!repository.existsById(pet2Id)) {
            throw new PetNotFoundException("Pet of ID: " + pet2Id + " not found", pet2Id);
        }

        RelationDatabaseEntity matchRelation = new RelationDatabaseEntity(pet1Id, pet2Id, "MATCH");
        relationRepository.getRepository().save(matchRelation);
        return true;
    }

    /**
     * Remove pet2 from pet1's swiped list
     * @param pet1Id
     * @param pet2Id
     * @return whether the swipe was successful
     */
    @Override
    public boolean unswipePets(int pet1Id, int pet2Id) throws PetNotFoundException {
        if (!repository.existsById(pet1Id)) {
            throw new PetNotFoundException("Pet of ID: " + pet1Id + " not found", pet1Id);
        }

        if (!repository.existsById(pet2Id)) {
            throw new PetNotFoundException("Pet of ID: " + pet2Id + " not found", pet2Id);
        }

        relationRepository.getRepository().delete(new RelationDatabaseEntity(pet1Id, pet2Id, "SWIPE"));
        return true;
    }

    /**
     * Add pet2 to pet1's rejected list
     * @param pet1Id
     * @param pet2Id
     * @return whether the rejection was successful
     */
    @Override
    public boolean rejectPets(int pet1Id, int pet2Id) throws PetNotFoundException {
        if (!repository.existsById(pet1Id)) {
            throw new PetNotFoundException("Pet of ID: " + pet1Id + " not found", pet1Id);
        }

        if (!repository.existsById(pet2Id)) {
            throw new PetNotFoundException("Pet of ID: " + pet2Id + " not found", pet2Id);
        }

        RelationDatabaseEntity matchRelation = new RelationDatabaseEntity(pet1Id, pet2Id, "REJECT");
        relationRepository.getRepository().save(matchRelation);
        return true;
    }

    /**
     * Return a list of pet ids that the given pet has swiped on
     * @param petId
     * @return a list of pet ids that the given pet has swiped on
     */
    @Override
    public List<Integer> fetchSwipedOn(int petId) throws PetNotFoundException {
        Optional<PetDatabaseEntity> searchResult = repository.findById(petId);

        if (searchResult.isPresent()) {
            return relationRepository.getRepository().findAllByFromIdAndRelationType(petId, "SWIPE").stream().map(RelationDatabaseEntity::getToId).collect(Collectors.toList());
        } else {
            throw new PetNotFoundException("Pet of ID: " + petId + " not found", petId);
        }
    }

    @Override
    public List<Integer> fetchRejected(int petId) throws PetNotFoundException {
        Optional<PetDatabaseEntity> searchResult = repository.findById(petId);

        if (searchResult.isPresent()) {
            PetDatabaseEntity pet = searchResult.get();
            return relationRepository.getRepository().findAllByFromIdAndRelationType(petId, "REJECT").stream().map(RelationDatabaseEntity::getToId).collect(Collectors.toList());
        } else {
            throw new PetNotFoundException("Pet of ID: " + petId + " not found", petId);
        }
    }

    /**
     * Return a list of pet ids that the given pet has matched with
     * @param petId
     * @return a list of pet ids that the given pet has matched with
     */
    @Override
    public List<Integer> fetchMatches(int petId) throws PetNotFoundException {
        Optional<PetDatabaseEntity> searchResult = repository.findById(petId);

        if (searchResult.isPresent()) {
            PetDatabaseEntity pet = searchResult.get();
            return relationRepository.getRepository().findAllByFromIdAndRelationType(petId, "MATCH").stream().map(RelationDatabaseEntity::getToId).collect(Collectors.toList());
        } else {
            throw new PetNotFoundException("Pet of ID: " + petId + " not found", petId);
        }
    }

}
