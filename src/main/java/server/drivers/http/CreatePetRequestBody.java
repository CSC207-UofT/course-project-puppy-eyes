package server.drivers.http;

/**
 * Represents an HTTP request body for the "/pets/create" POST route.
 */
public class CreatePetRequestBody {
    private final String name;
    private final int age;
    private final int userId;
    private final String breed;
    private final String biography;

    public CreatePetRequestBody(int userId, String name, int age, String breed, String biography) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.biography = biography;
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
}
