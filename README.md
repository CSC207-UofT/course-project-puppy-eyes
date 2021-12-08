# Note

This repository contains the backend only. The HTTP request endpoints and the command line allow you to interact with the server in lieu of a front end application. To access the repository for the frontend part of the project (Android app), go to https://github.com/CSC207-UofT/cupet-frontend.

# API Documentation

Subject to change.


## Login/Generate Session Token
- **URL**:

/auth/login

- **Requires Authenication**

No

- **Method**

POST

- **Request Body Format**

`{
    email: <String>,
	password: <String>
 }`

- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
    jwt: <String>
  }
}`



## Create User
- **URL**:

/users/create

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

POST

- **Request Body Format**

`{
    firstName: <String>,
    lastName: <String>,
    currentAddress: <String>,
    currentCity: <String>,
    password: <String>,
    emailAddress: <String>
 }`

- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
    userId: <String>,
    firstName: <String>,
    lastName: <String>,
    currentAddress: <String>,
    currentCity: <String>,
    email: <String>
  }
}`



## Fetch User Account
- **URL**:

/users/account?userId=[userId]

- **Method**

GET

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Request Body Format**

N/A

- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
    firstName: <String>,
    lastName: <String>,
    currentAddress: <String>,
    currentCity: <String>,
    email: <String>
  }
}`
  

## Edit User Profile
- **URL**:

/users/editprofile

- **Method**

POST

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Request Body Format**

`{ 
    userId: <Integer>,
    newBiography: <String>,
    newPhoneNumber: <String>,
    newInstagram: <String>,
    newFacebook: <String>
}`

- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
    userId: <Integer>,
		newBiography: <String>,
		newPhoneNumber: <String>,
		newInstagram: <String>,
		newFacebook: <String>
  }
}`

## Edit User Account
- **URL**:

/users/editaccount

- **Method**

POST

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Request Body Format**

`{ 
	userId: <Integer>,
	newFirstName: <String>,
	newLastName: <String>,
	newAddress: <String>,
	newCity: <String>,
	newPassword: <String>,
	newEmail: <String>
}`

- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
    userId: <Integer>,
    newFirstName: <String>,
    newLastName: <String>,
    newCurrentAddress: <String>,
    newCurrentCity: <String>,
    newEmail: <String>
  }
}`


## Fetch User Profile
- **URL**:

/users/profile?userId=[userId]

- **Method**

GET

- **Requires Authenication**

No

- **Request Body Format**

N/A
- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
    firstName: <Integer>,
    lastName: <String>,
    biography: <String>,
    phoneNumber: <String>,
    email: <String>,
    instagram: <String>,
	facebook: <String>,
	profileImgUrl: <String> (empty string if non-existant)
  }
}`


## Create Pet
- **URL**:

/pets/create

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

POST

- **Request Body Format**

`{
    userId: <String>,
    name: <String>,
    age: <String>,
    breed: <String>,
    biography: <String>
 }`

- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
    userId: <String>,
    petId: <String>,
    name: <String>,
    age: <String>,
    breed: <String>,
    biography: <String>
  }
}`


## Fetch Pet Profile
- **URL**:

/pets/profile?petId=[petId]

- **Requires Authenication**

No

- **Method**

GET

- **Request Body Format**

N/A

- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
    name: <String>,
    age: <String>,
    breed: <String>,
    biography: <String>,
	profileImgUrl: <String> (empty string if non-existant)
  }
}`


## Edit Pet Profile
- **URL**:

/pets/editprofile

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

POST

- **Request Body Format**

`{
    userId: <String>,
	petId: <String>,
    newName: <String>,
    newAge: <String>,
    newBreed: <String>,
    newBiography: <String>
 }`

- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
    petId: <String>,
    name: <String>,
    age: <String>,
    breed: <String>,
    biography: <String>
  }
}`


## Swipe Pets
- **URL**:

/pets/swipe

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

POST

- **Request Body Format**

`{
    pet1Id: <Integer>,
	pet2Id: <Integer>
 }`

- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>
}`


## Unswipe Pets
- **URL**:

/pets/unswipe

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

POST

- **Request Body Format**

`{
    pet1Id: <Integer>,
	pet2Id: <Integer>
 }`

- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>
}`


## Reject Pets
- **URL**:

/pets/reject

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

POST

- **Request Body Format**

`{
    pet1Id: <Integer>,
	pet2Id: <Integer>
 }`

- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>
}`



## Unmatch Pets
- **URL**:

/pets/unmatch

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

POST

- **Request Body Format**

`{
    pet1Id: <Integer>,
	pet2Id: <Integer>
 }`

- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>
}`


## Fetch Pet Swipes
- **URL**:

/pets/fetchswipes?petId=[petId]

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

GET

- **Request Body Format**

N/A
- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
	petIds: [<Integer>]
  }
}`


## Fetch Pet Matches
- **URL**:

/pets/fetchmatches?petId=[petId]

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

GET

- **Request Body Format**

N/A
- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
	petIds: [<Integer>]
  }
}`


## Fetch User's Pets
- **URL**:

/users/fetchpets?userId=[userId]

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

GET

- **Request Body Format**

N/A
- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
	petIds: [<Integer>]
  }
}`


## Generate Potential Pet Matches
- **URL**:

/pets/generatepotentialmatches?petId=[petId]

- **Requires Authenication**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

GET

- **Request Body Format**

N/A
- **Response**

`{ 
  isSuccess: <boolean>,
  message: <String>,
  data: {
	petIds: [<Integer>]
  }
}`


## Set User Profile Image
- **URL**:

/users/setprofileimage

- **Requires Authentiction**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

POST

- **Request Body Format**

`
{
	base64Encoded: <String>
}
`

- **Response**

`
{
	isSuccess: <boolean>,
	message: <String>,
	data: {
		url: <String>
	}
}
`


## Set Pet Profile Image
- **URL**:

/pets/setprofileimage

- **Requires Authentiction**

Yes, requires header key-value pair: `Authorization: Bearer <JWT Token>`

- **Method**

POST

- **Request Body Format**

`
{
	petId: <String>,
	base64Encoded: <String>
}
`

- **Response**

`
{
	isSuccess: <boolean>,
	message: <String>,
	data: {
		url: <String>,
		assetId: <String>
	}
}
`