package server.entities;

public enum UserType {

    COMMON_USER("Common User"),
    PREMIUM_USER("Premium User");

    private final String name;

    private UserType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
