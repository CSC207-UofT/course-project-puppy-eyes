package server.entities;

public class UserBuilder implements IUserBuilder {

    private final String firstName, lastName, password, currentCity, email;
    private String currentAddress, phoneNumber, instagram, facebook, biography;
    private int id;

    public UserBuilder(String firstName, String lastName, String password, String currentCity, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.currentCity = currentCity;
        this.email = email;
    }

    @Override
    public User create() {
        User user = new User(firstName, lastName, currentAddress, currentCity, password, email);
        user.setBiography(this.biography);
        user.getContactInfo().setPhoneNumber(this.phoneNumber);
        user.getContactInfo().setInstagram(this.instagram);
        user.getContactInfo().setFacebook(this.facebook);
        user.setId(id);

        return user;
    }

    @Override
    public IUserBuilder reset() {
        this.currentAddress = "";
        this.phoneNumber = "";
        this.instagram = "";
        this.facebook = "";
        return this;
    }

    @Override
    public IUserBuilder id(int id) {
        this.id = id;
        return this;
    }

    @Override
    public IUserBuilder biography(String biography) {
        this.biography = biography;
        return this;
    }

    @Override
    public IUserBuilder currentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
        return this;
    }

    @Override
    public IUserBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public IUserBuilder instagram(String instagram) {
        this.instagram = instagram;
        return this;
    }

    @Override
    public IUserBuilder facebook(String facebook) {
        this.facebook = facebook;
        return this;
    }
}
