package server.use_cases.repo_abstracts;

/**
 * A class defining the structure of the IPetRepository response
 * for the fetchPetProfile method.
 */
public class PetRepositoryPetProfileFetcherResponse {
    private final String name;
    private final int age;
    private String breed;
    private String biography;


    public PetRepositoryPetProfileFetcherResponse(String name, int age, String breed, String biography) {
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
}
