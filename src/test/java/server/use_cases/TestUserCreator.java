package server.use_cases;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserCreator {
    private static UserCreator userCreator;

    @BeforeAll
    public static void setUp() {
        userCreator = new UserCreator(new DummyUserRepository());
    }

    @Test()
    public void TestSuccessCreateUser() {
        UserCreatorResponseModel expected = new UserCreatorResponseModel(true, "joe",
                "bob", "8888 Joe St", "Toronto", "joe@email.com", "0");

        UserCreatorResponseModel actual = userCreator.createUser(new UserCreatorRequestModel("joe",
                "bob", "8888 Joe St", "Toronto", "12345", "joe@email.com"));

        assertEquals(expected, actual);
    }
}
