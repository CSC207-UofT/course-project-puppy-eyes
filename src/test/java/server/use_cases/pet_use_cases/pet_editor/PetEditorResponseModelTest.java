package server.use_cases.pet_use_cases.pet_editor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import server.use_cases.pet_use_cases.pet_creator.PetCreatorResponseModel;

class PetEditorResponseModelTest {

    @Test
    void testEquals() {
        PetEditorResponseModel response1 = new PetEditorResponseModel("koko", 3, "Cat", "hihi", "4");
        PetEditorResponseModel response2 = new PetEditorResponseModel("koko", 3, "Cat", "hihi", "4");
        boolean result = response1.equals(response2);

        assertEquals(result, true);
    }

    @Test
    void getName() {
        PetEditorResponseModel response = new PetEditorResponseModel("koko", 3, "Cat", "hihi", "4");
        String expected = "koko";
        String actual = response.getName();

        assertEquals(expected, actual);
    }

    @Test
    void getAge() {
        PetEditorResponseModel response = new PetEditorResponseModel("koko", 3, "Cat", "hihi", "4");
        int expected = 3;
        int actual = response.getAge();

        assertEquals(expected, actual);
    }

    @Test
    void getPetId() {
        PetEditorResponseModel response = new PetEditorResponseModel("koko", 3, "Cat", "hihi", "4");
        String expected = "4";
        String actual = response.getPetId();

        assertEquals(expected, actual);
    }

    @Test
    void getBreed() {
        PetEditorResponseModel response = new PetEditorResponseModel("koko", 3, "Cat", "hihi", "4");
        String expected = "Cat";
        String actual = response.getBreed();

        assertEquals(expected, actual);
    }

    @Test
    void getBiography() {
        PetEditorResponseModel response = new PetEditorResponseModel("koko", 3, "Cat", "hihi", "4");
        String expected = "hihi";
        String actual = response.getBiography();

        assertEquals(expected, actual);
    }
}