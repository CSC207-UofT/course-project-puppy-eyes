package server.drivers.dbEntities;

import javax.persistence.*;

@Entity
@Table(name = "pets")
public class PetDatabaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "breed")
    private String breed;

    @Column(name = "biography")
    private String biography;


    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private UserDatabaseEntity user;

    public PetDatabaseEntity(int userId, String name, int age, String biography, String breed) {
        super();
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.biography = biography;

    }

    public PetDatabaseEntity() {}

    public UserDatabaseEntity getUser() {
        return this.user;
    }

    public void setUser(UserDatabaseEntity user) {
        this.user = user;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

}
