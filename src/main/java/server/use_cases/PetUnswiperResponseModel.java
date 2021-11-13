package server.use_cases;

public class PetUnswiperResponseModel {

    private final boolean isSuccess;

    public PetUnswiperResponseModel(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

}