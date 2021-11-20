package server.controllers;

import server.use_cases.*;
import server.use_cases.repo_abstracts.ResponseModel;
import server.use_cases.repo_abstracts.ResponsePresenter;
import server.use_cases.repo_abstracts.UseCaseOutputBoundary;

/**
 * A controller that handles all functions relating to pet data.
 */
public class PetController implements IPetController {
    PetCreatorInputBoundary petCreator;
    PetProfileFetcherInputBoundary profileFetcher;
    PetEditorInputBoundary petEditor;
    PetSwiperInputBoundary petSwiper;
    PetUnswiperInputBoundary petUnswiper;
    PetRejectorInputBoundary petRejector;
    PetSwipesFetcherInputBoundary petSwipesFetcher;
    PetMatchesFetcherInputBoundary petMatchesFetcher;
    PetMatchesGeneratorInputBoundary petMatchesGenerator;
    UseCaseOutputBoundary responsePresenter;

    public PetController(PetCreatorInputBoundary petCreator, PetSwiperInputBoundary petSwiper, PetProfileFetcherInputBoundary profileFetcher,
                         PetEditorInputBoundary petEditor, PetRejectorInputBoundary petRejector, PetUnswiperInputBoundary petUnswiper,
                         PetSwipesFetcherInputBoundary petSwipesFetcher, PetMatchesFetcherInputBoundary petMatchesFetcher,
                         PetMatchesGeneratorInputBoundary petMatchesGenerator, IJSONPresenter jsonPresenter) {
        this.petCreator = petCreator;
        this.profileFetcher = profileFetcher;
        this.petEditor = petEditor;
        this.petSwiper = petSwiper;
        this.petSwipesFetcher = petSwipesFetcher;
        this.petMatchesFetcher = petMatchesFetcher;
        this.petRejector = petRejector;
        this.petUnswiper = petUnswiper;
        this.petMatchesGenerator = petMatchesGenerator;
        this.responsePresenter = new ResponsePresenter(jsonPresenter);
    }

    /**
     * Create a new pet.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param userId
     * @param name          the pet's name
     * @param age           the pet's age
     * @param breed         the pet's breed
     * @param biography     the pet's biography
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false",
     *          // if successful:
     *          data: {
     *              userId: the id of the pet's owner
     *              petId: the pet's id
     *              name: the pet's name
     *              age: the pet's age
     *              breed: the pet's breed
     *              biography: the pet's biography
     *          }
     *          // else,
     *          data: null
     *      }
     */
    @Override
    public ResponseModel createPet(boolean fromTerminal, String headerUserId, String userId, String name, String age, String breed, String biography) {
        PetCreatorRequestModel request = new PetCreatorRequestModel(headerUserId, userId, name, age, breed, biography);
        request.setFromTerminal(fromTerminal);
        return petCreator.createPet(request);
    }

    /**
     * Given a pet id, fetch a pet's profile information.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId         the pet's id
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false",
     *          // if successful:
     *          data: {
     *              name: the pet's name
     *              age: the pet's age
     *              breed: the pet's breed
     *              biography: the pet's biography
     *          }
     *          // else,
     *          data: null
     *      }
     */
    @Override
    public ResponseModel fetchPetProfile(boolean fromTerminal, String headerUserId, String petId) {
        PetProfileFetcherRequestModel request = new PetProfileFetcherRequestModel(headerUserId, petId);
        request.setFromTerminal(fromTerminal);
        return profileFetcher.fetchPetProfile(request);
    }

    /**
     * Add the pet with pet2Id to the pet with pet1Id's reject list
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param pet1Id
     * @param pet2Id
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false",
     *          message: The response message
     *      }
     */
    @Override
    public ResponseModel rejectPets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id) {
        PetRejectorRequestModel request = new PetRejectorRequestModel(headerUserId, pet1Id, pet2Id);
        request.setFromTerminal(fromTerminal);
        return petRejector.rejectPets(request);
    }

    /**
     * Return a list of pet ids that the pet may potentially want to match with
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false",
     *          // if successful:
     *          data: {
     *              petIds: [pet_id_1, pet_id_2, ..., pet_id_n]
     *          }
     *          // else,
     *          data: null
     *      }
     */
    @Override
    public ResponseModel generatePotentialMatches(boolean fromTerminal, String headerUserId, String petId) {
        PetMatchesGeneratorRequestModel request = new PetMatchesGeneratorRequestModel(headerUserId, petId);
        request.setFromTerminal(fromTerminal);
        return petMatchesGenerator.generatePotentialMatches(request);
    }

    /**
     * Add the pet with pet2Id to the pet with pet1Id's swiped list
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param pet1Id
     * @param pet2Id
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false",
     *          message: The response message
     *      }
     */
    @Override
    public ResponseModel swipePets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id) {
        PetSwiperRequestModel request = new PetSwiperRequestModel(headerUserId, pet1Id, pet2Id);
        request.setFromTerminal(fromTerminal);
        return petSwiper.swipe(request);
    }

    /**
     * Remove the pet with pet2Id to the pet with pet1Id's swiped list
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param pet1Id
     * @param pet2Id
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false",
     *          message: The response message
     *      }
     */
    @Override
    public ResponseModel unswipePets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id) {
        PetUnswiperRequestModel request = new PetUnswiperRequestModel(headerUserId, pet1Id, pet2Id);
        request.setFromTerminal(fromTerminal);
        return petUnswiper.unswipePets(request);
    }

    /**
     * Return a list of pet ids that the pet with petId1 has swiped on
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false",
     *          // if successful:
     *          data: {
     *              petIds: [pet_id_1, pet_id_2, ..., pet_id_n]
     *          }
     *          // else,
     *          data: null
     *      }
     */
    @Override
    public ResponseModel fetchPetSwipes(boolean fromTerminal, String headerUserId, String petId) {
        PetSwipesFetcherRequestModel request = new PetSwipesFetcherRequestModel(headerUserId, petId);
        request.setFromTerminal(fromTerminal);
        return petSwipesFetcher.fetchPetSwipes(request);
    }

    /**
     * Return a list of pet ids that are matched with the pet with this pet id
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false",
     *          // if successful:
     *          data: {
     *              petIds: [pet_id_1, pet_id_2, ..., pet_id_n]
     *          }
     *          // else,
     *          data: null
     *      }
     */
    @Override
    public ResponseModel fetchPetMatches(boolean fromTerminal, String headerUserId, String petId) {
        PetMatchesFetcherRequestModel request = new PetMatchesFetcherRequestModel(headerUserId, petId);
        request.setFromTerminal(fromTerminal);
        return petMatchesFetcher.fetchPetMatches(request);
    }

    /**
     * Given new information, edit a pet's information in the database.
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId         user entered pet id
     * @param newName       user entered pet's new name
     * @param newAge        user entered pet's new age
     * @param newBreed      user entered pet's new breed
     * @param newBiography  user entered pet's new biography
     * @return a ResponseModel containing:
     * {
     *       isSuccess: "true"/"false"
     *       // If successful, then include:
     *       data: {
     *          petId: the id of pet
     *          name: the new name of pet
     *          age: the new age of pet
     *          breed: the new breed of pet
     *          biography: the new biography of pet
     *       }
     *  }
     */
    public ResponseModel editPet(boolean fromTerminal, String headerUserId, String petId, String newName, String newAge, String newBreed, String newBiography) {
        PetEditorRequestModel request = new PetEditorRequestModel(headerUserId, petId, newName, newAge, newBreed, newBiography);
        request.setFromTerminal(fromTerminal);
        return petEditor.editPet(request);
    }
}
