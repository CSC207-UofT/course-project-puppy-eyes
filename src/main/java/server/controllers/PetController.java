package server.controllers;

import server.use_cases.*;
import java.util.HashMap;

/**
 * A controller that handles all functions relating to pet data.
 */
public class PetController implements IPetController {
    PetCreatorInputBoundary petCreator;
    // PetProfileFetcherInputBoundary profileFetcher;
    IJSONPresenter jsonPresenter;

    public PetController(PetCreatorInputBoundary petCreator, IJSONPresenter jsonPresenter) {
        this.petCreator = petCreator;
        this.jsonPresenter = jsonPresenter;
    }

    @Override
    public String createPet(String name, int age) {

        PetCreatorRequestModel request = new PetCreatorRequestModel(name, age);
        PetCreatorResponseModel response = petCreator.createPet(request);

        HashMap<String, String> responseMap = new HashMap<String, String>() {{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("name", response.getName());
            put("age", String.valueOf(response.getAge())); // TODO: Are you sure you want this in String?
            put("petId", response.getPetId());
        }};

        return jsonPresenter.toJSON(responseMap);
    }

    // TODO: implements following methods

    @Override
    public String fetchPetProfile() {
        return null;
    }

    @Override
    public String matchPet() {
        return null;
    }

    @Override
    public String editPet() {
        return null;
    }
}
