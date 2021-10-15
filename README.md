## Note

This repository contains the backend only. The HTTP request endpoints and the command line allow you to interact with the server in lieu of a front end application.

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