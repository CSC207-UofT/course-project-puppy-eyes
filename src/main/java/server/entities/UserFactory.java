package server.entities;

public class UserFactory {

    public User createUser(UserType type, String firstName, String lastName, String currentAddress, String currentCity, String password, String email) {
        User user;

        switch (type) {
            case COMMON_USER:
                user = new CommonUser(firstName, lastName, currentAddress, currentCity, password, email);
                break;
            case PREMIUM_USER:
            default:
                user = null;
                break;
        }

        return user;
    }

}
