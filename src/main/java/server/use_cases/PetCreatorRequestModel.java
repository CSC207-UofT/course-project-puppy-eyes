package server.use_cases;

/**
 * An object defining the request type for PetCreator.createPet
 */
public class PetCreatorRequestModel {
    private final int userId;
    private final String name;
    private final int age;

    public PetCreatorRequestModel(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
