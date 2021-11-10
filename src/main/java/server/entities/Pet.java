package server.entities;

public abstract class Pet {
    private final String name;
    private final int age;
    private String biography;
    private String breed;
    private int id;

    public Pet(String name, int age){
        this.name = name;
        this.age = age;
        this.biography = "";
        this.breed = "";
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

    public void setBreed(String breed) {
        this.breed = breed;
    }

}