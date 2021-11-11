package server.controllers;

import server.use_cases.*;
import server.use_cases.repo_abstracts.PetNotFoundException;

import java.util.HashMap;

/**
 * A controller that handles all functions relating to pet data.
 */
public class PetController implements IPetController {
    PetCreatorInputBoundary petCreator;
    PetProfileFetcherInputBoundary profileFetcher;
    PetEditorInputBoundary petEditor;
    IJSONPresenter jsonPresenter;

    public PetController(PetCreatorInputBoundary petCreator, PetProfileFetcherInputBoundary profileFetcher, PetEditorInputBoundary petEditor, IJSONPresenter jsonPresenter) {
        this.petCreator = petCreator;
        this.profileFetcher = profileFetcher;
        this.petEditor = petEditor;
        this.jsonPresenter = jsonPresenter;
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
    public String createPet(String name, int age, String breed, String biography) {

        PetCreatorRequestModel request = new PetCreatorRequestModel(name, age, breed, biography);
        PetCreatorResponseModel response = petCreator.createPet(request);

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
    @Override
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

    // TODO: implements following method
    @Override
    public String matchPet() {
        return null;
    }
}
