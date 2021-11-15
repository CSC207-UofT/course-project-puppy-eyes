package server.use_cases.repo_abstracts;

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

    public String getHeaderUserId() {
        return this.headerUserId;
    }

    public String getUserId() {
        return this.userId;
    }

    /**
     * When true, the headerUserId field is ignored and the request will always be authorized.
     * @param fromTerminal
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
     * @return whether the request is authorized
     */
    public boolean isRequestAuthorized() {
        return this.fromTerminal || (this.userId != null && this.headerUserId.equals(userId));
    }

}
