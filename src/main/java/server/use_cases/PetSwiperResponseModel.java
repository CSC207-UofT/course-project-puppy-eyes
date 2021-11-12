package server.use_cases;

public class PetSwiperResponseModel {

    private final boolean isSuccess;

    public PetSwiperResponseModel(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

}
