package server.entities;

public abstract class Pet {
    private String name;
    private String biography;
    private int age;

    public Pet(String name, String biography, int age){
        this.name = name;
        this.biography = biography;
        this.age = age;
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

    abstract String getBreed();


}