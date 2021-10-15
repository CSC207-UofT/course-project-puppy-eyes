package server.controllers;

/**
 * An interface representing a presenter that converts
 * application data (in the form of Java objects) to JSON strings.
 */
public interface IJSONPresenter {
    /**
     * Given a basic Java object, return a JSON representation of the object
     * containing the object's public instance attributes.
     */
    String toJSON(Object obj);
}
