package server.controllers;

import server.use_cases.pet_use_cases.pet_creator.PetCreatorInputBoundary;
import server.use_cases.pet_use_cases.pet_creator.PetCreatorRequestModel;
import server.use_cases.pet_use_cases.pet_editor.PetEditorInputBoundary;
import server.use_cases.pet_use_cases.pet_editor.PetEditorRequestModel;
import server.use_cases.pet_use_cases.pet_gallery_images_fetcher.PetGalleryImagesFetcher;
import server.use_cases.pet_use_cases.pet_gallery_images_fetcher.PetGalleryImagesFetcherInputBoundary;
import server.use_cases.pet_use_cases.pet_gallery_images_fetcher.PetGalleryImagesFetcherRequestModel;
import server.use_cases.pet_use_cases.pet_image_adder.PetImageAdderInputBoundary;
import server.use_cases.pet_use_cases.pet_image_adder.PetImageAdderRequestModel;
import server.use_cases.pet_use_cases.pet_image_remover.PetImageRemoverInputBoundary;
import server.use_cases.pet_use_cases.pet_image_remover.PetImageRemoverRequestModel;
import server.use_cases.pet_use_cases.pet_interactor.PetInteractionType;
import server.use_cases.pet_use_cases.pet_matches_fetcher.PetMatchesFetcherInputBoundary;
import server.use_cases.pet_use_cases.pet_matches_fetcher.PetMatchesFetcherRequestModel;
import server.use_cases.pet_use_cases.pet_matches_generator.PetMatchesGeneratorInputBoundary;
import server.use_cases.pet_use_cases.pet_matches_generator.PetMatchesGeneratorRequestModel;
import server.use_cases.pet_use_cases.pet_profile_fetcher.PetProfileFetcherInputBoundary;
import server.use_cases.pet_use_cases.pet_profile_fetcher.PetProfileFetcherRequestModel;
import server.use_cases.pet_use_cases.pet_profile_image_changer.PetProfileImageChangerInputBoundary;
import server.use_cases.pet_use_cases.pet_profile_image_changer.PetProfileImageChangerRequestModel;
import server.use_cases.pet_use_cases.pet_interactor.PetInteractorInputBoundary;
import server.use_cases.pet_use_cases.pet_interactor.PetInteractorRequestModel;
import server.use_cases.pet_use_cases.pet_swipes_fetcher.PetSwipesFetcherInputBoundary;
import server.use_cases.pet_use_cases.pet_swipes_fetcher.PetSwipesFetcherRequestModel;
import server.use_cases.ResponseModel;
import server.adapters.UseCaseOutputBoundary;
import server.use_cases.profile_image_fetcher.ProfileImageFetcherInputBoundary;
import server.use_cases.profile_image_fetcher.ProfileImageFetcherRequestModel;

/**
 * A controller that handles all functions relating to pet data.
 */
public class PetController implements IPetController {

    PetCreatorInputBoundary petCreator;
    PetProfileFetcherInputBoundary profileFetcher;
    PetEditorInputBoundary petEditor;
    PetInteractorInputBoundary petInteractor;
    PetSwipesFetcherInputBoundary petSwipesFetcher;
    PetMatchesFetcherInputBoundary petMatchesFetcher;
    PetMatchesGeneratorInputBoundary petMatchesGenerator;
    PetProfileImageChangerInputBoundary petProfileImageChanger;
    PetImageAdderInputBoundary petImageAdder;
    PetImageRemoverInputBoundary petImageRemover;
    ProfileImageFetcherInputBoundary profileImageFetcher;
    PetGalleryImagesFetcherInputBoundary petGalleryImagesFetcher;
    UseCaseOutputBoundary responsePresenter;

    public PetController(PetCreatorInputBoundary petCreator,
                         PetProfileFetcherInputBoundary profileFetcher,
                         PetEditorInputBoundary petEditor,
                         PetInteractorInputBoundary petInteractor,
                         PetSwipesFetcherInputBoundary petSwipesFetcher,
                         PetMatchesFetcherInputBoundary petMatchesFetcher,
                         PetMatchesGeneratorInputBoundary petMatchesGenerator,
                         PetProfileImageChangerInputBoundary petProfileImageChanger,
                         PetImageAdderInputBoundary petImageAdder,
                         PetImageRemoverInputBoundary petImageRemover,
                         ProfileImageFetcherInputBoundary profileImageFetcher,
                         PetGalleryImagesFetcherInputBoundary petGalleryImagesFetcher,
                         UseCaseOutputBoundary responsePresenter) {
        this.petCreator = petCreator;
        this.profileFetcher = profileFetcher;
        this.petEditor = petEditor;
        this.petSwipesFetcher = petSwipesFetcher;
        this.petMatchesFetcher = petMatchesFetcher;
        this.petInteractor = petInteractor;
        this.petMatchesGenerator = petMatchesGenerator;
        this.petProfileImageChanger = petProfileImageChanger;
        this.petImageAdder = petImageAdder;
        this.petImageRemover = petImageRemover;
        this.profileImageFetcher = profileImageFetcher;
        this.petGalleryImagesFetcher = petGalleryImagesFetcher;
        this.responsePresenter = responsePresenter;
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
     *          isSuccess: "true"/"false"
     *          message: The response message
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
     *          isSuccess: "true"/"false"
     *          message: The response message
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
     *          isSuccess: "true"/"false"
     *          message: The response message
     *      }
     */
    @Override
    public ResponseModel rejectPets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id) {
        PetInteractorRequestModel request = new PetInteractorRequestModel(headerUserId, pet1Id, pet2Id, PetInteractionType.REJECT);
        request.setFromTerminal(fromTerminal);
        return petInteractor.interact(request);
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
     *          isSuccess: "true"/"false"
     *          message: The response message
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
     *          isSuccess: "true"/"false"
     *          message: The response message
     *      }
     */
    @Override
    public ResponseModel swipePets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id) {
        PetInteractorRequestModel request = new PetInteractorRequestModel(headerUserId, pet1Id, pet2Id, PetInteractionType.SWIPE);
        request.setFromTerminal(fromTerminal);
        return petInteractor.interact(request);
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
     *          isSuccess: "true"/"false"
     *          message: The response message
     *      }
     */
    @Override
    public ResponseModel unswipePets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id) {
        PetInteractorRequestModel request = new PetInteractorRequestModel(headerUserId, pet1Id, pet2Id, PetInteractionType.UNSWIPE);
        request.setFromTerminal(fromTerminal);
        return petInteractor.interact(request);
    }

    /**
     * Remove the pets from each other's matched list
     *
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param pet1Id
     * @param pet2Id
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false"
     *          message: The response message
     *      }
     */
    @Override
    public ResponseModel unmatchPets(boolean fromTerminal, String headerUserId, String pet1Id, String pet2Id) {
        PetInteractorRequestModel request = new PetInteractorRequestModel(headerUserId, pet1Id, pet2Id, PetInteractionType.UNMATCH);
        request.setFromTerminal(fromTerminal);
        return petInteractor.interact(request);
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
     *          isSuccess: "true"/"false"
     *          message: The response message
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
     *          isSuccess: "true"/"false"
     *          message: The response message,
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
     * Set's the pet profile to the image represented by the Base64 encoding.
     * @param headerUserId
     * @param petId
     * @param base64Encoded
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false"
     *          message: "The response message,
     *          // if successful:
     *         data: {
     *              url: the url to the image
     *              assetId: the id of the asset
     *         }
     *          // else,
     *          data: null
     *      }
     */
    @Override
    public ResponseModel setPetProfile(String headerUserId, String petId, String base64Encoded) {
        PetProfileImageChangerRequestModel request = new PetProfileImageChangerRequestModel(headerUserId, petId, base64Encoded);
        ResponseModel response = petProfileImageChanger.changeProfileImage(request);
        return response;
    }

    /**
     * Add to a pet's image gallery the image represented by the Base64 encoding.
     * @param headerUserId
     * @param petId
     * @param base64Encoded
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false"
     *          // if successful:
     *          message: The response message,
     *          data: {
     *              url: the url to the image
     *              assetId: the id of the asset
     *          }
     *          // else,
     *          data: null
     *      }
     */
    @Override
    public ResponseModel addPetImage(String headerUserId, String petId, String base64Encoded) {
        PetImageAdderRequestModel request = new PetImageAdderRequestModel(headerUserId, petId, base64Encoded);
        ResponseModel response = petImageAdder.addImage(request);
        return response;
    }
    /**
     * Remove a pet's image gallery the image represented by the asset id.
     * @param headerUserId
     * @param petId
     * @param assetId
     * @return a ResponseModel containing:
     *      {
     *          isSuccess: "true"/"false"
     *          message: The response message
     *      }
     */
    @Override
    public ResponseModel removePetImage(String headerUserId, String petId, String assetId) {
        PetImageRemoverRequestModel request = new PetImageRemoverRequestModel(headerUserId, petId, assetId);
        ResponseModel response = petImageRemover.removeImage(request);
        return response;
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
     *       message: The response message
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

    /**
     * Return a URL containing this pet's profile image
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId         the pet's id
     * @return a ResponseModel containing:
     * {
     *       isSuccess: "true"/"false"
     *       message: The response message
     *       // If successful, then include:
     *       data: {
     *          url: the url of the image
     *       }
     *  }
     */
    public ResponseModel fetchPetProfileImage(boolean fromTerminal, String headerUserId, String petId) {
        ProfileImageFetcherRequestModel request = new ProfileImageFetcherRequestModel(headerUserId, petId, false);
        request.setFromTerminal(fromTerminal);
        return profileImageFetcher.fetchProfileImage(request);
    }

    /**
     * Return a URL containing this pet's gallery images
     * @param fromTerminal  whether this action is being run from command line prompt
     * @param headerUserId  the id of the user performing this action, if not from terminal. If `fromTerminal`
     *                      is true, this field does nothing.
     * @param petId         the pet's id
     * @return a ResponseModel containing:
     * {
     *       isSuccess: "true"/"false"
     *       message: The response message
     *       // If successful, then include:
     *       data: {
     *          urls: the urls of the image
     *       }
     *  }
     */
    public ResponseModel fetchPetGalleryImages(boolean fromTerminal, String headerUserId, String petId) {
        PetGalleryImagesFetcherRequestModel request = new PetGalleryImagesFetcherRequestModel(headerUserId, petId);
        request.setFromTerminal(fromTerminal);
        return petGalleryImagesFetcher.fetchImages(request);
    }
}
