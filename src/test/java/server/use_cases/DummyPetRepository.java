package server.use_cases;

import server.entities.Pet;
import server.entities.PetBuilder;
import server.use_cases.repo_abstracts.IPetRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A dummy class representing a pet entity in the repository
 */
class DummyPetRepositoryEntity {

    private int id, userId;
    private String name;
    private int age;
    private String breed;
    private String biography;
    private List<Integer> swipedOn, matches, rejected;

    public DummyPetRepositoryEntity(int id, int userId, String name, int age, String breed, String biography) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.biography = biography;
        this.swipedOn = new ArrayList<>();
        this.rejected = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public String getBiography() {
        return biography;
    }

    public List<Integer> getSwipedOn() {
        return this.swipedOn;
    }

    public List<Integer> getRejected() {
        return this.rejected;
    }

    public List<Integer> getMatches() {
        return this.matches;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}

/**
 * A dummy pet repository that stores users in memory.
 */
public class DummyPetRepository implements IPetRepository {
    private final ArrayList<DummyPetRepositoryEntity> pets;
    private int currentMaxId;
    private DummyUserRepository dummyUserRepository;

    public DummyPetRepository(DummyUserRepository dummyUserRepository) {
        this.dummyUserRepository = dummyUserRepository;
        pets = new ArrayList<>();
        currentMaxId = -1;
    }

    /**
     * Create a new pet and save it in the repository.
     *
     * @param userId    the user id of the pet's owner;
     * @param name      the pet's name;
     * @param age       the Pet's age;
     * @param breed     the Pet's breed;
     * @param biography the Pet's biography;
     * @return new pet's id;
     */
    @Override
    public int createPet(int userId, String name, int age, String breed, String biography) {
        currentMaxId++;
        int petId = currentMaxId;
        DummyPetRepositoryEntity dbPet = new DummyPetRepositoryEntity(petId, userId, name, age, breed, biography);
        dummyUserRepository.addPet(userId, petId);
        pets.add(dbPet);
        return petId;
    }

    /**
     * Fetch a pet's by a given pet id.
     *
     * @param petId the pet's id;
     * @return Pet;
     */
    @Override
    public Pet fetchPet(int petId) {
        DummyPetRepositoryEntity dbPet = pets.stream().filter(pet -> pet.getId() == petId).findFirst().orElse(null);

        if (dbPet != null) {
            Pet pet = new PetBuilder(dbPet.getUserId(), dbPet.getName(), dbPet.getAge(), dbPet.getBreed()).
                    biography(dbPet.getBiography()).
                    id(dbPet.getId()).
                    create();
            return pet;
        } else {
            return null;
        }
    }

    /**
     * Edit a pet's information, given all new information.
     *
     * @param petId        the pet's id;
     * @param newName      the pet's new name;
     * @param newAge       the pet's new age;
     * @param newBreed     the pet's new breed;
     * @param newBiography the pet's new biography;
     * @return if the editing is successfully done;
     */
    @Override
    public boolean editPet(int petId, String newName, int newAge, String newBreed, String newBiography) {
        DummyPetRepositoryEntity dbPet = pets.stream().filter(pet -> pet.getId() == petId).findFirst().orElse(null);

        if (dbPet != null) {
            dbPet.setName(newName);
            dbPet.setAge(newAge);
            dbPet.setBreed(newBreed);
            dbPet.setBiography(newBiography);
            return true;
        } else return false;
    }

    /**
     * Add pet2 to pet1's swiped list
     *
     * @param pet1Id
     * @param pet2Id
     * @return whether the swipe was successful
     */
    @Override
    public boolean swipePets(int pet1Id, int pet2Id) {
        DummyPetRepositoryEntity dbPet1 = pets.stream().filter(pet -> pet.getId() == pet1Id).findFirst().orElse(null);
        DummyPetRepositoryEntity dbPet2 = pets.stream().filter(pet -> pet.getId() == pet2Id).findFirst().orElse(null);

        if (dbPet1 == null || dbPet2 == null) {
            return false;
        }

        dbPet1.getSwipedOn().add(dbPet2.getId());
        return true;
    }

    /**
     * Add pet1 and pet2 to each other's matched list.
     *
     * @param pet1Id
     * @param pet2Id
     * @return whether the swipe was successful
     */
    @Override
    public boolean matchPets(int pet1Id, int pet2Id) {
        DummyPetRepositoryEntity dbPet1 = pets.stream().filter(pet -> pet.getId() == pet1Id).findFirst().orElse(null);
        DummyPetRepositoryEntity dbPet2 = pets.stream().filter(pet -> pet.getId() == pet2Id).findFirst().orElse(null);

        if (dbPet1 == null || dbPet2 == null) {
            return false;
        }

        dbPet1.getMatches().add(dbPet2.getId());
        dbPet2.getMatches().add(dbPet1.getId());
        return true;
    }

    /**
     * Remove pet2 from pet1's swiped list
     *
     * @param pet1Id
     * @param pet2Id
     * @return whether the swipe was successful
     */
    @Override
    public boolean unswipePets(int pet1Id, int pet2Id) {
        DummyPetRepositoryEntity dbPet1 = pets.stream().filter(pet -> pet.getId() == pet1Id).findFirst().orElse(null);
        DummyPetRepositoryEntity dbPet2 = pets.stream().filter(pet -> pet.getId() == pet2Id).findFirst().orElse(null);

        if (dbPet1 == null || dbPet2 == null) {
            return false;
        }

        dbPet1.getSwipedOn().remove((Integer) dbPet2.getId());
        return true;
    }

    /**
     * Add pet2 to pet1's rejected list
     *
     * @param pet1Id
     * @param pet2Id
     * @return whether the rejection was successful
     */
    @Override
    public boolean rejectPets(int pet1Id, int pet2Id) {
        DummyPetRepositoryEntity dbPet1 = pets.stream().filter(pet -> pet.getId() == pet1Id).findFirst().orElse(null);
        DummyPetRepositoryEntity dbPet2 = pets.stream().filter(pet -> pet.getId() == pet2Id).findFirst().orElse(null);

        if (dbPet1 == null || dbPet2 == null) {
            return false;
        }

        dbPet1.getRejected().add(dbPet2.getId());
        return true;
    }

    /**
     * Remove pet1 and pet2 from each other's matched list.
     *
     * @param pet1Id
     * @param pet2Id
     * @return whether the rejection was successful
     */
    @Override
    public boolean unmatchPets(int pet1Id, int pet2Id) {
        DummyPetRepositoryEntity dbPet1 = pets.stream().filter(pet -> pet.getId() == pet1Id).findFirst().orElse(null);
        DummyPetRepositoryEntity dbPet2 = pets.stream().filter(pet -> pet.getId() == pet2Id).findFirst().orElse(null);

        if (dbPet1 == null || dbPet2 == null) {
            return false;
        }

        dbPet1.getMatches().remove(dbPet2.getId());
        dbPet2.getMatches().remove(dbPet1.getId());
        return true;
    }

    /**
     * Return a list of pet ids that the given pet has swiped on
     *
     * @param petId
     * @return a list of pet ids that the given pet has swiped on
     */
    @Override
    public List<Integer> fetchSwipedOn(int petId) {
        DummyPetRepositoryEntity dbPet = pets.stream().filter(pet -> pet.getId() == petId).findFirst().orElse(null);

        if (dbPet == null) {
            return null;
        }

        return dbPet.getSwipedOn();
    }

    /**
     * Return a list of pet ids that the given pet has rejected
     *
     * @param petId
     * @return a list of pet ids that the given pet has rejected
     */
    @Override
    public List<Integer> fetchRejected(int petId) {
        DummyPetRepositoryEntity dbPet = pets.stream().filter(pet -> pet.getId() == petId).findFirst().orElse(null);

        if (dbPet == null) {
            return null;
        }

        return dbPet.getRejected();
    }

    /**
     * Return a list of pet ids that the given pet has matched with
     *
     * @param petId
     * @return a list of pet ids that the given pet has matched with
     */
    @Override
    public List<Integer> fetchMatches(int petId) {
        DummyPetRepositoryEntity dbPet = pets.stream().filter(pet -> pet.getId() == petId).findFirst().orElse(null);

        if (dbPet == null) {
            return null;
        }

        return dbPet.getMatches();
    }

}
