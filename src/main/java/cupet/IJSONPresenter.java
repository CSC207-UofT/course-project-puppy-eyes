package cupet;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IJSONPresenter {
    /* Transform a Java Object into a JSON string */
    String toJSON(Object obj) throws JsonProcessingException;
}
