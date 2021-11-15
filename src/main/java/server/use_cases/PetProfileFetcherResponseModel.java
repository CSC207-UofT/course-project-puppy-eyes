package server.use_cases;

import server.use_cases.repo_abstracts.ResponseData;

import java.util.Objects;

/**
 * An object defining the response type for
 * PetProfileFetcherInputBoundary.fetchPetProfile
 */
public class PetProfileFetcherResponseModel extends ResponseData {

    private final String name;
    private final int age;
    private String breed;
    private String biography;

    public PetProfileFetcherResponseModel(String name, int age, String breed, String biography) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetProfileFetcherResponseModel that = (PetProfileFetcherResponseModel) o;
        return   Objects.equals(name, that.name)
                && Objects.equals(age, that.age)
                && Objects.equals(breed, that.breed)
                && Objects.equals(biography, that.biography);
    }
}
