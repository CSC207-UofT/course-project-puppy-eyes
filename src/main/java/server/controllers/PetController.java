package server.controllers;

import server.use_cases.*;
import java.util.HashMap;

/**
 * A controller that handles all functions relating to pet data.
 */
public class PetController implements IPetController {
    PetCreatorInputBoundary petCreator;
    PetProfileFetcherInputBoundary profileFetcher;
    IJSONPresenter jsonPresenter;

    public PetController(PetCreatorInputBoundary petCreator, PetProfileFetcherInputBoundary profileFetcher, IJSONPresenter jsonPresenter) {
        this.petCreator = petCreator;
        this.profileFetcher = profileFetcher;
        this.jsonPresenter = jsonPresenter;
    }

    /**
     * Create a new pet.
     *
     * @param name The pet's name;
     * @param age The pet's age;
     * @return a JSON structure containing:
     *      * {
     *      *  isSuccess: "true"/"false"
     *      *  // If successful, then include the following
     *      *  name: the name of pet
     *      *  age: the age of pet
     *      * }
     */
    @Override
    public String createPet(String name, int age) {

        PetCreatorRequestModel request = new PetCreatorRequestModel(name, age);
        PetCreatorResponseModel response = petCreator.createPet(request);

        HashMap<String, String> responseMap = new HashMap<String, String>() {{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("name", response.getName());
            put("age", String.valueOf(response.getAge()));
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

    // TODO: implements following methods

    @Override
    public String matchPet() {
        return null;
    }

    @Override
    public String editPet() {
        return null;
    }
}
