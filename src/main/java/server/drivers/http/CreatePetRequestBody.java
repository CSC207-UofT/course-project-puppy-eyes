package server.drivers.http;

public class CreatePetRequestBody {
    private final String name;
    private final int age;
    private final int userId;

    public CreatePetRequestBody(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
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
}
