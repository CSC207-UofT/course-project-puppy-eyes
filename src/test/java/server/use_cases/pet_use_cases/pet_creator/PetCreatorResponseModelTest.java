package server.use_cases.pet_use_cases.pet_creator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PetCreatorResponseModelTest {

    @Test
    void testEquals() {
        PetCreatorResponseModel response1 = new PetCreatorResponseModel("0", "0", "Koko", "10", "Dog", "Hi");
        PetCreatorResponseModel response2 = new PetCreatorResponseModel("0", "0", "Koko", "10", "Dog", "Hi");
        boolean result = response1.equals(response2);

        assertEquals(result, true);
    }

    @Test
    void getName() {
        PetCreatorResponseModel response = new PetCreatorResponseModel("0", "0", "Koko", "10", "Dog", "Hi");
        String expected = "Koko";
        String actual = response.getName();

        assertEquals(expected, actual);
    }

    @Test
    void getAge() {
        PetCreatorResponseModel response = new PetCreatorResponseModel("0", "0", "Koko", "10", "Dog", "Hi");
        String expected = "10";
        String actual = response.getAge();

        assertEquals(expected, actual);
    }

    @Test
    void getPetId() {
        PetCreatorResponseModel response = new PetCreatorResponseModel("0", "0", "Koko", "10", "Dog", "Hi");
        String expected = "10";
        String actual = response.getAge();

        assertEquals(expected, actual);
    }

    @Test
    void getUserId() {
        PetCreatorResponseModel response = new PetCreatorResponseModel("1", "0", "Koko", "10", "Dog", "Hi");
        String expected = "0";
        String actual = response.getUserId();

        assertEquals(expected, actual);
    }

    @Test
    void getBreed() {
        PetCreatorResponseModel response = new PetCreatorResponseModel("1", "0", "Koko", "10", "Dog", "Hi");
        String expected = "Dog";
        String actual = response.getBreed();

        assertEquals(expected, actual);
    }

    @Test
    void getBiography() {
        PetCreatorResponseModel response = new PetCreatorResponseModel("1", "0", "Koko", "10", "Dog", "Hi");
        String expected = "Hi";
        String actual = response.getBiography();

        assertEquals(expected, actual);
    }
}