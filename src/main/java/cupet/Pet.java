import java.util.Random;

public abstract class Pet {
    private String name;
    private String biography;
    private int age;
    private String Breed;
    private final int id;

    /**
     * Creates a Pet, giving them a name, biography, age, and a randomly generated ID.
     *
     * @param name The name of this pet
     * @param biography  A description of this pet
     * @param age     The age of this pet
     */
    public Pet(String name, String biography, int age){
        this.name = name;
        this.biography = biography;
        this.age = age;
        Random id = new Random();
        this.id = Math.abs(id.nextInt());
    }
    /**
     * The getter for the name of the pet.
     */
    public String getName(){
        return this.name;
    }

    /**
     * The getter for the age of the pet.
     */
    public int getAge(){
        return this.age;
    }

    /**
     * The getter for the biography of the pet.
     */
    public String getBiography(){
        return this.biography;
    }

    /**
     * The getter for the breed of the pet.
     */
    abstract String getBreed();


}
