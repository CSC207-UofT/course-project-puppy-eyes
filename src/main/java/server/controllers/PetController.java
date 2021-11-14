package server.controllers;

import server.use_cases.*;
import server.use_cases.repo_abstracts.PetNotFoundException;

import java.util.HashMap;
import java.util.List;

/**
 * A controller that handles all functions relating to pet data.
 */
public class PetController implements IPetController {
    PetCreatorInputBoundary petCreator;
    PetProfileFetcherInputBoundary profileFetcher;
    PetEditorInputBoundary petEditor;
    IJSONPresenter jsonPresenter;
    PetSwiperInputBoundary petSwiper;
    PetUnswiperInputBoundary petUnswiper;
    PetRejectorInputBoundary petRejector;
    PetSwipesFetcherInputBoundary petSwipesFetcher;
    PetMatchesFetcherInputBoundary petMatchesFetcher;

    public PetController(PetCreatorInputBoundary petCreator, PetSwiperInputBoundary petSwiper, PetProfileFetcherInputBoundary profileFetcher,
                         PetEditorInputBoundary petEditor, PetRejectorInputBoundary petRejector, PetUnswiperInputBoundary petUnswiper,
                         PetSwipesFetcherInputBoundary petSwipesFetcher, PetMatchesFetcherInputBoundary petMatchesFetcher,
                         IJSONPresenter jsonPresenter) {
        this.petCreator = petCreator;
        this.profileFetcher = profileFetcher;
        this.petEditor = petEditor;
        this.jsonPresenter = jsonPresenter;
        this.petSwiper = petSwiper;
        this.petSwipesFetcher = petSwipesFetcher;
        this.petMatchesFetcher = petMatchesFetcher;
        this.petRejector = petRejector;
        this.petUnswiper = petUnswiper;
    }

    /**
     * Create a new pet.
     *
     *
     * @param userId
     * @param name          the pet's name
     * @param age           the pet's age
     * @param breed         the pet's breed
     * @param biography     the pet's biography
     * @return a JSON structure containing:
     *      * {
     *      *  isSuccess: "true"/"false"
     *      *  // If successful, then include the following
     *      *  name: the name of pet
     *      *  age: the age of pet
     *      * }
     */
    @Override
    public String createPet(String userId, String name, int age, String breed, String biography) {
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
     * @param petId     the pet's id
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
     * Add the pet with pet2Id to the pet with pet1Id's reject list
     * @param pet1Id
     * @param pet2Id
     * @return "true" if successful, "false" otherwise
     */
    @Override
    public String rejectPets(int pet1Id, int pet2Id) {
        PetRejectorRequestModel request = new PetRejectorRequestModel(pet1Id, pet2Id);
        PetRejectorResponseModel response = petRejector.rejectPets(request);
        return String.valueOf(response.isSuccess());
    }

    /**
     * Add the pet with pet2Id to the pet with pet1Id's swiped list
     * @param pet1Id
     * @param pet2Id
     * @return "true" if successful, "false" otherwise
     */
    @Override
    public String swipePets(int pet1Id, int pet2Id) {
        PetSwiperRequestModel request = new PetSwiperRequestModel(pet1Id, pet2Id);
        PetSwiperResponseModel response = petSwiper.swipe(request);
        return String.valueOf(response.isSuccess());
    }

    /**
     * Remove the pet with pet2Id to the pet with pet1Id's swiped list
     * @param pet1Id
     * @param pet2Id
     * @return "true" if successful, "false" otherwise
     */
    @Override
    public String unswipePets(int pet1Id, int pet2Id) {
        PetUnswiperRequestModel request = new PetUnswiperRequestModel(pet1Id, pet2Id);
        PetUnswiperResponseModel response = petUnswiper.unswipePets(request);
        return String.valueOf(response.isSuccess());
    }

    /**
     * Return a list of pet ids that the pet with petId1 has swiped on
     * @param petId
     * @return a JSON structure containing:
     *      {
     *          isSuccess: "true"/"false",
     *          // if successful:
     *          petIds: [pet_id_1, pet_id_2, ..., pet_id_n]
     *          // else,
     *          petIds: null
     *      }
     */
    @Override
    public String fetchPetSwipes(int petId) {
        PetSwipesFetcherRequestModel request = new PetSwipesFetcherRequestModel(String.valueOf(petId));
        PetSwipesFetcherResponseModel response = petSwipesFetcher.fetchPetSwipes(request);

        HashMap<String, String> responseMap = new HashMap<String, String>() {{
            put("isSuccess", response.isSuccess() ? "true": "false");
            put("petIds", jsonPresenter.toJSON(response.getPetIds()));
        }};

        return jsonPresenter.toJSON(responseMap);
    }

    /**
     * Return a list of pet ids that are matched with the pet with this pet id
     * @param petId
     * @return a JSON structure containing:
     *      {
     *          isSuccess: "true"/"false",
     *          // if successful:
     *          petIds: [pet_id_1, pet_id_2, ..., pet_id_n]
     *          // else,
     *          petIds: null
     *      }
     */
    @Override
    public String fetchPetMatches(int petId) {
        PetMatchesFetcherRequestModel request = new PetMatchesFetcherRequestModel(String.valueOf(petId));
        PetMatchesFetcherResponseModel response = petMatchesFetcher.fetchPetMatches(request);

        HashMap<String, String> responseMap = new HashMap<String, String>() {{
            put("isSuccess", response.isSuccess() ? "true" : "false");
            put("petIds", jsonPresenter.toJSON(response.getPetIds()));
        }};

        return jsonPresenter.toJSON(responseMap);
    }

    /**
     * Given new information, edit a pet's information in the database.
     *
     * @param petId         user entered pet id
     * @param newName       user entered pet's new name
     * @param newAge        user entered pet's new age
     * @param newBreed      user entered pet's new breed
     * @param newBiography  user entered pet's new biography
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
