package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONPresenter implements IJSONPresenter{
    private final ObjectMapper mapper;

    public JSONPresenter() {
        mapper = new ObjectMapper();
    }

    /**
     * Given a basic Java object, return a JSON representation of the object
     * containing the object's public instance attributes. If this conversion fails,
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