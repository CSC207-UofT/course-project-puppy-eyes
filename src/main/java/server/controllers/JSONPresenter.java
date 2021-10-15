package server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A presenter that converts application data (in the form of Java objects)
 * to JSON strings.
 */
public class JSONPresenter implements IJSONPresenter{
    private final ObjectMapper mapper;

    public JSONPresenter() {
        mapper = new ObjectMapper();
    }

    /**
     * Return a JSON representation of obj. If this conversion fails,
     * return a generic JSON string in the format:
     *
     * {
     *     "isSuccess": "false";
     * }
     */
    @Override
    public String toJSON(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "{\"isSuccess\":\"false\"}";
        }
    }
}