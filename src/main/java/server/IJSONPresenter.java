package server;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IJSONPresenter {
    /**
     * Transform a Java Object into a JSON string
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    String toJSON(Object obj) throws JsonProcessingException;
}