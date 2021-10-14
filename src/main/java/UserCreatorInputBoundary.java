/**
 * An input boundary for the "create user" use case.
 */
public interface UserCreatorInputBoundary {
    UserCreatorResponseModel createUser(UserCreatorRequestModel request);
}
