public class UserCreatorResponseModel {
    private User user;

    public UserCreatorResponseModel(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

}
