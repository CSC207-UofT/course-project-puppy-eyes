package server.controllers;

import server.use_cases.*;

import java.util.HashMap;

/**
 * A controller that handles all functions relating to pet data.
 */
public class PetController implements IPetController {
    PetCreatorInputBoundary petCreator;
    PetProfileFetcherInputBoundary profileFetcher;
    PetEditorInputBoundary petEditor;
    IJSONPresenter jsonPresenter;
    PetSwiperInputBoundary petSwiper;

    public PetController(PetCreatorInputBoundary petCreator, PetSwiperInputBoundary petSwiper, PetProfileFetcherInputBoundary profileFetcher,
                         PetEditorInputBoundary petEditor, IJSONPresenter jsonPresenter) {
        this.petCreator = petCreator;
        this.profileFetcher = profileFetcher;
        this.petEditor = petEditor;
        this.jsonPresenter = jsonPresenter;
        this.petSwiper = petSwiper;
    }

    /**
     * Create a new pet.
     *
     * @param name The pet's name;
     * @param age The pet's age;
     * @param breed The pet's breed;
     * @param biography The pet's biography;
     * @return a JSON structure containing:
     *      * {
     *      *  isSuccess: "true"/"false"
     *      *  // If successful, then include the following
     *      *  name: the name of pet
     *      *  age: the age of pet
     *      * }
     */
    @Override
    public String createPet(int userId, String name, int age, String breed, String biography) {
        PetCreatorRequestModel request = new PetCreatorRequestModel(userId, name, age, breed, biography);
        PetCreatorResponseModel response = petCreator.createPet(request);

        HashMap<String, String> responseMap = new HashMap<String, String>() {{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("userId", response.getUserId());
            put("name", response.getName());
            put("age", response.getAge());
            put("breed", response.getBreed());
            put("biography", response.getBiography());
            put("petId", response.getPetId());
        }};

        return jsonPresenter.toJSON(responseMap);
    }

    /**
     * Given a pet id, fetch a pet's profile information.
     *
     * @param petId
     * @return a JSON structure containing:
     *      * {
     *      *  isSuccess: "true"/"false"
     *      *  // If successful, then include the following
     *      *  name: the name of pet
     *      *  age: the age of pet
     *      * }
     */
    @Override
    public String fetchPetProfile(String petId) {
        PetProfileFetcherRequestModel request = new PetProfileFetcherRequestModel(petId);

        PetProfileFetcherResponseModel response = profileFetcher.fetchPetProfile(request);

        HashMap<String, String> responseMap = new HashMap<String, String>() {{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("name", response.getName());
            put("age", String.valueOf(response.getAge()));
            put("breed", response.getBreed());
            put("biography", response.getBiography());
        }};

        return jsonPresenter.toJSON(responseMap);
    }

    @Override
    public String matchPets(int pet1Id, int pet2Id) {
        return null;
    }

    @Override
    public String swipePets(int pet1Id, int pet2Id) {
        PetSwiperRequestModel request = new PetSwiperRequestModel(pet1Id, pet2Id);
        PetSwiperResponseModel response = petSwiper.swipe(request);
        return String.valueOf(response.isSuccess());
    }

    @Override
    public String unswipePets(int pet1Id, int pet2Id) {
        return null;
    }

     /**
     * Given new information, edit a pet's information in the database.
     *
     * @param petId User entered pet id;
     * @param newName User entered pet's new name;
     * @param newAge User entered pet's new age;
     * @param newBreed User entered pet's new breed;
     * @param newBiography User entered pet's new biography;
     * @return a JSON structure containing:
     *      * {
     *      *  isSuccess: "true"/"false"
     *      *  // If successful, then include the following
     *      *  petId: the id of pet
     *      *  name: the new name of pet
     *      *  age: the new age of pet
     *      *  breed: the new breed of pet
     *      *  biography: the new biography of pet
     *      * }
     */
    public String editPet(String petId, String newName, int newAge, String newBreed, String newBiography) {
        PetEditorRequestModel request = new PetEditorRequestModel(petId, newName, newAge, newBreed, newBiography);
        PetEditorResponseModel response = petEditor.editPet(request);

        HashMap<String, String> responseMap = new HashMap<String, String>() {{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("name", response.getName());
            put("age", String.valueOf(response.getAge()));
            put("breed", response.getBreed());
            put("biography", response.getBiography());
            put("petId", response.getPetId());
        }};

        return jsonPresenter.toJSON(responseMap);
    }
}
