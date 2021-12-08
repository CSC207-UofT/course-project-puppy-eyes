package server.drivers.mocks;

import server.controllers.ISessionController;
import server.use_cases.ResponseModel;

public class MockSessionController extends MockController  implements ISessionController {
    @Override
    public ResponseModel generateJwt(String email, String password) {
        return makeMockResponseData();
    }
}
