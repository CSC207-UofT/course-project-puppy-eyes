package server.use_cases;

public class AuthRequestModel {

    private boolean fromTerminal;
    public final String headerUserId;
    private String userId;

    public AuthRequestModel(String headerUserId, String userId) {
        this.headerUserId = headerUserId;
        this.userId = userId;
    }

    public AuthRequestModel(String headerUserId) {
        this.headerUserId = headerUserId;
    }

    /**
     * Return the userId that is attached to the request header.
     * This value should never be null since any requests missing a userId in its header will
     * be denied by AuthFilter. This value is compared to userId to determine whether
     * the request should be authorized.
     *
     * @return the userId attached to the request *header*
     */
    public String getHeaderUserId() {
        return this.headerUserId;
    }

    /**
     * Return the userId that this action is trying to perform on.
     * This value differs from headerUserId in that this value is specified in the request body.
     *
     * @return the userId attached to the request *body*
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * When true, the headerUserId field is ignored and the request will always be authorized.
     *
     * @param fromTerminal whether from terminal
     */
    public void setFromTerminal(boolean fromTerminal) {
        this.fromTerminal = fromTerminal;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Return whether the request is authorized.
     * True if this request is coming from the terminal, or if the user id obtained from decoding the JWT token in
     * the request header matches the user id that the user is trying to access
     *
     * @return whether the request is authorized
     */
    public boolean isRequestAuthorized() {
        return this.fromTerminal || (this.userId != null && this.headerUserId.equals(userId));
    }

    /**
     * Return whether this request came from the command line or not.
     *
     * @return whether the request came from the command line
     */
    public boolean isFromTerminal() {
        return this.fromTerminal;
    }

}
