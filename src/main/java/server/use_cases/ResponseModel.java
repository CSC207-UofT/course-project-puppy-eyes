package server.use_cases;

public class ResponseModel {

    public final boolean isSuccess;
    public final String message;
    public final ResponseData data;

    public ResponseModel(boolean isSuccess, String message, ResponseData data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public ResponseModel(boolean isSuccess, String errorMessage) {
        this.isSuccess = isSuccess;
        this.message = errorMessage;
        this.data = null;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public String getMessage() {
        return this.message;
    }

    public ResponseData getResponseData() {
        return this.data;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ResponseModel))
            return false;

        ResponseModel other = (ResponseModel) o;

        return other.isSuccess == this.isSuccess && other.message.equals(this.message) &&
                ((this.data == null && other.getResponseData() == null) || other.getResponseData().equals(this.data));
    }

}