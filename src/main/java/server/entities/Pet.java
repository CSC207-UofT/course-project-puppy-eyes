package server.entities;

public abstract class Pet {
    private final String name;
    private final int age;
    private String biography;
    private final String breed;
    private int id;

    public Pet(String name, int age, String breed, String biography){
        this.name = name;
        this.age = age;
        this.biography = biography;
        this.breed = breed;
    }
    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public String getBiography(){
        return this.biography;
    }

    public String getBreed() {
        return this.breed;
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}