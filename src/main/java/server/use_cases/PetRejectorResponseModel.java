package server.use_cases;

public class PetRejectorResponseModel {

    private final boolean isSuccess;

    public PetRejectorResponseModel(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

}