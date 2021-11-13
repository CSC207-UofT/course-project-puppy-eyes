package server.use_cases;

import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.PetRepositoryPetProfileFetcherResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * A dummy class representing a pet entity in the repository
 */
class DummyPetRepositoryEntity {
    private String name;
    private int age;
    private String breed;
    private String biography;

    public DummyPetRepositoryEntity(String name, int age, String breed, String biography) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.biography = biography;
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

    public DummyPetRepository() {
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
        pets.add(new DummyPetRepositoryEntity(name, age, breed, biography));
        return petId;
    }

    /**
     * Fetch a pet's profile information by a given pet id.
     *
     * @param petId the pet's id;
     * @return PetRepositoryPetProfileFetcherResponse;
     * @throws PetNotFoundException ;
     */
    @Override
    public PetRepositoryPetProfileFetcherResponse fetchPetProfile(int petId) throws PetNotFoundException {
        if (petId >= 0 && petId <= currentMaxId) {
            DummyPetRepositoryEntity pet = pets.get(petId);
            return new PetRepositoryPetProfileFetcherResponse(pet.getName(), pet.getAge(),
                    pet.getBreed(), pet.getBiography());
        } else {
            throw new PetNotFoundException("Pet with ID: " + petId + " not found.");
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
        if (petId >= 0 && petId <= currentMaxId) {
            DummyPetRepositoryEntity pet = pets.get(petId);
            pet.setName(newName);
            pet.setAge(newAge);
            pet.setBreed(newBreed);
            pet.setBiography(newBiography);
            return true;
        } else {
            return false;
        }
    }

    // TODO implement following methods
    @Override
    public List<Integer> fetchPetSwipes(int petId) throws PetNotFoundException {
        return null;
    }

    @Override
    public List<Integer> fetchPetMatches(int petId) throws PetNotFoundException {
        return null;
    }

}
