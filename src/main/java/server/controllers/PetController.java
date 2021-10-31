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

        HashMap<String, String> responseMap = new HashMap<>() {{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("name", response.getName());
            put("age", String.valueOf(response.getAge()));
            put("petId", response.getPetId());
        }};

        return jsonPresenter.toJSON(responseMap);
    }

    @Override
    public String fetchPetProfile(String petId) {
        return null;
    }

    @Override
    public String matchPet() {
        return null;
    }

    @Override
    public String editPet(String petId) {
        return null;
    }
}
