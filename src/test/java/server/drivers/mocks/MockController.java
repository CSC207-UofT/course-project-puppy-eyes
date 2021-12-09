package server.drivers.mocks;

import server.use_cases.ResponseModel;

public class MockController {
    ResponseModel makeMockResponseData () {
        return new ResponseModel(true, "test message");
    }
}
