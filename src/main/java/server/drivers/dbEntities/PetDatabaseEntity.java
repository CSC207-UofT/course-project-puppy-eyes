package server.drivers.dbEntities;

import javax.persistence.*;

@Entity
@Table(name = "pets")
public class PetDatabaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "biography")
    private String biography;

    @Column(name = "breed")
    private String breed;

    public PetDatabaseEntity(String name, int age, String biography, String breed) {
        super();
        this.name = name;
        this.age = age;
        this.biography = biography;
        this.breed = breed;
    }

    public PetDatabaseEntity() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBiography() {
        return biography;
    }

    public String getBreed() {
        return breed;
    }
}
