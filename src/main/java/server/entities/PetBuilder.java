package server.entities;

public class PetBuilder implements IPetBuilder {

    private final String name, breed;
    private String biography = "";
    private final int userId, age;
    private int id = -1;

    public PetBuilder(int userId, String name, int age, String breed) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    @Override
    public Pet create() {
        Pet pet = new Pet(userId, name, age, breed, biography);
        return pet;
    }

    @Override
    public IPetBuilder reset() {
        this.biography = "";
        this.id = -1;
        return this;
    }

    @Override
    public IPetBuilder id(int id) {
        this.id = id;
        return this;
    }

    @Override
    public IPetBuilder biography(String biography) {
        this.biography = biography;
        return this;
    }

}