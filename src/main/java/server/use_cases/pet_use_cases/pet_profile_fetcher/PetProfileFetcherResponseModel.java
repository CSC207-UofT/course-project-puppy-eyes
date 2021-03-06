package server.use_cases.pet_use_cases.pet_profile_fetcher;

import server.use_cases.ResponseData;

/**
 * An object defining the response type for
 * PetProfileFetcherInputBoundary.fetchPetProfile
 */
public class PetProfileFetcherResponseModel extends ResponseData {

    private final int userId;
    private final String name;
    private final int age;
    private final String breed;
    private final String biography;
    private final String profileImgUrl;

    public PetProfileFetcherResponseModel(int userId, String name, int age, String breed, String biography, String profileImgUrl) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.biography = biography;
        this.profileImgUrl = profileImgUrl;
    }

    public int getUserId() {
        return userId;
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

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetProfileFetcherResponseModel other = (PetProfileFetcherResponseModel) o;
        return userId == other.userId && name.equals(other.name) && age == other.age && breed.equals(other.breed)
                && biography.equals(other.biography) && profileImgUrl.equals(other.profileImgUrl);
    }
}
