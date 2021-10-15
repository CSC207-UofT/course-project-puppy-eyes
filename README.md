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
9. Front end application receives this JSON string response and performs any necessary actions (i.e. update UI)

## Testing

### Starting up the server

ServerApplication.java contains the main method that hooks into Spring. When the backend is started, the server will run on http://localhost:8080 (port 8080 by default).
To test that the HTTPSGateway is correctly receiving the endpoints, you may use following curl command:

`curl localhost:8080/`

which should return "Welcome to Cupet"

Or, you can directly head to http://localhost:8080/ on any web browser.

### Create a User

To test user creation, use the following curl command:

`curl -X POST -d "{ \"firstName\": \"<FirstName>\", \"lastName\": \"<LastName>\", \"homeAddress\": \"<HomeAddress>\", \"password\": \"<Password>\", \"email\": \"<Email>\" }" -H "Content-Type: application/json" http://localhost:8080/users/create`

Replace the values with <> with any reasonable values.
This sends a POST request to the endpoint "/users/create" with a request body that contains the keys `firstName`, `lastName`, `homeAddress`, `password`, and `email`.

To test that this action successfuly wrote to the database, either use the following curl command:

`curl localhost:8080/users/all`

or head to http://localhost:8080/users/all/ on any web browser.

The user you just created should appear in the returned array.