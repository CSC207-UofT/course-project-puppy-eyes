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

    @Column(name = "breed")
    private String breed;

    @Column(name = "biography")
    private String biography;


    public PetDatabaseEntity(String name, int age, String breed, String biography) {
        super();
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.biography = biography;

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

    public String getBreed() {
        return breed;
    }

    public String getBiography() {
        return biography;
    }
}
