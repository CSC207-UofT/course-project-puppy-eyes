package server;
public interface IJSONPresenter {
    /**
     * Given a basic Java object, return a JSON representation of the object
     * containing the object's public instance attributes.
     */
    String toJSON(Object obj);
}
