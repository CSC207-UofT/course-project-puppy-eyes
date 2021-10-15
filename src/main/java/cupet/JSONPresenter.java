package cupet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONPresenter implements IJSONPresenter{
    private final ObjectMapper mapper;

    public JSONPresenter() {
        mapper = new ObjectMapper();
    }

    @Override
    public String toJSON(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
