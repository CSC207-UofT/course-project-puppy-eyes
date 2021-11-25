package server.entities;

public class CommonUser extends User {

    public CommonUser(String firstName, String lastName, String currentAddress, String currentCity, String password, String email) {
        super(firstName, lastName, currentAddress, currentCity, password, email);
    }

    @Override
    public UserType getType() {
        return UserType.COMMON_USER;
    }

}
