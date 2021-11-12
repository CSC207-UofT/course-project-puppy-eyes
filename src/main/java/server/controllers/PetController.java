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
    PetSwiperInputBoundary petSwiper;

    public PetController(PetCreatorInputBoundary petCreator, PetSwiperInputBoundary petSwiper, IJSONPresenter jsonPresenter) {
        this.petCreator = petCreator;
        this.jsonPresenter = jsonPresenter;
        this.petSwiper = petSwiper;
    }

    @Override
    public String createPet(int userId, String name, int age) {

        PetCreatorRequestModel request = new PetCreatorRequestModel(userId, name, age);
        PetCreatorResponseModel response = petCreator.createPet(request);

        HashMap<String, String> responseMap = new HashMap<String, String>() {{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("userId", response.getUserId());
            put("name", response.getName());
            put("age", response.getAge());
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

    @Override
    public String editPet() {
        return null;
    }
}
