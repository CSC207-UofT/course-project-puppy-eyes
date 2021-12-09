package server.use_cases;

public class ResponseModel {

    public final boolean isSuccess, isForbidden;
    public final String message;
    public final ResponseData data;

    /**
     * Return a ResponseModel responding to a request with the given data
     *
     * @param message
     * @param data
     */
    public ResponseModel(boolean isSuccess, String message, ResponseData data) {
        this.isSuccess = isSuccess;
        this.isForbidden = false;
        this.message = message;
        this.data = data;
    }

    /**
     * Return a ResponseModel responding to a with the given message with no data
     *
     * @param isSuccess
     * @param message
     */
    public ResponseModel(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.isForbidden = false;
        this.message = message;
        this.data = null;
    }

    /**
     * Create a ResponseModel responding to forbidden request
     */
    public ResponseModel() {
        this.isSuccess = false;
        this.isForbidden = true;
        this.message = "You are not authorized to make this request.";
        this.data = null;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public boolean isForbidden() {
        return this.isForbidden;
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