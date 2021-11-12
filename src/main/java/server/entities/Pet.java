package server.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Pet {
    private final int userId;
    private final String name;
    private final int age;
    private String biography;
    private String breed;
    private int id;
    private List<Pet> swiped, matched;

    public Pet(int userId, String name, int age, String breed, String biography) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.biography = biography;
        this.breed = breed;
        this.swiped = new ArrayList<>();
        this.matched = new ArrayList<>();
    }

    public int getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getBiography() {
        return this.biography;
    }

    public String getBreed() {
        return this.breed;
    };

    public int getId() {
        return id;
    }

    public List<Pet> getSwiped() {
        return this.swiped;
    }

    public List<Pet> getMatched() {
        return this.matched;
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pet)) {
            return false;
        }

        return ((Pet) other).getId() == this.id;
    }

}