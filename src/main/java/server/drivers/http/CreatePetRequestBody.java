package server.drivers.http;

public class CreatePetRequestBody {
    private final String name;
    private final int age;

    public CreatePetRequestBody(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}
