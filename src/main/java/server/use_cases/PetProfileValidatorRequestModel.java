package server.use_cases;

/**
 * An object defining the request type for PetProfileValidator.validateProfile
 */
public class PetProfileValidatorRequestModel {

    private final String name;
    private final String age;
    private String breed;
    private String biography;

    public PetProfileValidatorRequestModel(String name, String age, String breed, String biography) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public String getBiography() {
        return biography;
    }
}
