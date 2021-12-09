package server.use_cases;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.controllers.JSONPresenter;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ResponsePresenterTest {
    ResponsePresenter responsePresenter;

    @BeforeEach
    public void setUp(){
        responsePresenter = new ResponsePresenter(new JSONPresenter());
    }

    @Test
    public void testFormatResponse(){
        ResponseModel response = new ResponseModel(true, "test message");

        JSONObject expected = new JSONObject(new HashMap<String, String>(){{
            put("isSuccess", "true");
            put("message", "test message");
            put("data", "null");
        }});

        String actual = responsePresenter.formatResponse(response);

        try {
            JSONObject actualObj = new JSONObject(actual);

            assertEquals(expected.get("isSuccess"), actualObj.get("isSuccess"));
            assertEquals(expected.get("message"), actualObj.get("message"));
            assertEquals(expected.get("data"), actualObj.get("data"));
        } catch (JSONException e) {
            fail("ResponsePresenter returned an invalid JSON string");
        }
    }
}