package server.use_cases;

/**
 * An object defining the request type for PetCreator.createPet
 */
public class PetCreatorRequestModel {
    private final String name;
    private final int age;

    public PetCreatorRequestModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
