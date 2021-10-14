## Scenario Walkthrough

This repository contains the back end for the Cupet app. The process of data flow for creating a user (signing up) starting with the front end is:
1. User launches front end application
2. User performs auth action (login, signup)
3. Input data is sent as an HTTP request to the backend API endpoint (HTTPSGateway)
4. In HTTPSGateway, a RestController which implements the endpoints defined by the interface APIGateway, the HTTP request is processed and sent to the UserController
5. UserController, which is responsible for managing all the use cases, calls the relevant use case
6. The use case, which has an implementation of UserRepositoryInterface injected in its constructor, executes the action and pushes to the repository, then returns a UserCreaterResponseModel object back to UserController
7. UserController takes the UserCreaterResponseModel and formats it into a JSON String and returns it back to HTTPSGateway
8. HTTPSGateway returns this JSON string as a response to the initial HTTP request
