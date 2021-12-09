package server.use_cases.pet_use_cases.pet_profile_fetcher;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PetProfileFetcherResponseModelTest {

    PetProfileFetcherResponseModel response = new PetProfileFetcherResponseModel(1, "Koko", 10, "Dog", "Hello", "");
    PetProfileFetcherResponseModel response2 = new PetProfileFetcherResponseModel(1, "Koko", 10, "Dog", "Hello", "");
    PetProfileFetcherResponseModel response3 = new PetProfileFetcherResponseModel(11, "Koko", 10, "Dog", "Hello", "");

    @Test
    void getUserId() {
        assertEquals(1, response.getUserId());
    }

    @Test
    void getName() {
        assertEquals("Koko", response.getName());
    }

    @Test
    void getAge() {
        assertEquals(10, response.getAge());
    }

    @Test
    void getBreed() {
        assertEquals("Dog", response.getBreed());
    }

    @Test
    void getBiography() {
        assertEquals("Hello", response.getBiography());
    }

    @Test
    void getProfileImgUrl() {
        assertEquals("", response.getProfileImgUrl());
    }

    @Test
    void testEquals() {
        assertEquals(true, response.equals(response2));
        assertEquals(false, response.equals(response3));
    }
}