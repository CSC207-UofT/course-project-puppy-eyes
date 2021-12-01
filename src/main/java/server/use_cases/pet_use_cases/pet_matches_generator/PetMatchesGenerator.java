package server.use_cases.pet_use_cases.pet_matches_generator;
import server.drivers.IGeocoderService;
import server.drivers.LatLng;
import server.entities.User;
import server.use_cases.ResponseModel;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.repo_abstracts.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A use case responsible for generating a list of matches for a given pet.
 */
public class PetMatchesGenerator implements PetMatchesGeneratorInputBoundary {

    private final IUserRepository userRepository;
    private final IPetRepository petRepository;
    private final PetActionValidatorInputBoundary petActionValidator;
    private final IGeocoderService geocoderService;

    public PetMatchesGenerator(IUserRepository userRepository,
                               IPetRepository petRepository,
                               PetActionValidatorInputBoundary petActionValidator,
                               IGeocoderService geocoderService) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        this.petActionValidator = petActionValidator;
        this.geocoderService = geocoderService;
    }

    @Override
    public ResponseModel generatePotentialMatches(PetMatchesGeneratorRequestModel request) {
        ResponseModel validateActionResponse = petActionValidator.validateAction(new PetActionValidatorRequestModel(
                request.isFromTerminal(), request.getHeaderUserId(), request.getPetId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        int petId = Integer.parseInt(request.getPetId());

        List<String> potentialMatches = new ArrayList<>();

        User currentUser = userRepository.fetchUser(petId);

        // Fetch all users from the database
        List<User> users = this.userRepository.fetchAllUsers();
        List<Integer> rejectedPets = this.petRepository.fetchRejected(petId);

        // backup code for if geocoding service is unavailable
//        for (User user : users) {
//            if (user.getId() == currentUser.getId())
//                continue;
//
//            List<Integer> petIds = this.userRepository.fetchUserPets(user.getId());
//
//            for (int id : petIds) {
//                // Only add the other user's pet if it's not on this pet's rejected list
//                if (rejectedPets.contains(id))
//                    continue;
//
//                potentialMatches.add(String.valueOf(id));
//            }
//        }

        // GeocoderService.getLatLng returns a List of LatLng objects, but here we are assuming that the current user's
        // current address and city are sufficiently specific to return a unique latitude-longitude tuple
        LatLng currentUserLatLng = this.geocoderService.getLatLng(currentUser.getCurrentAddress() + ", " + currentUser.getCurrentCity()).get(0);

        for (User otherUser : users) {
            // Do not fetch pets for the same user
            if (otherUser.getId() == currentUser.getId())
                continue;

            List<LatLng> otherLatLngs = geocoderService.getLatLng(otherUser.getCurrentAddress() + ", " + otherUser.getCurrentCity());

            for (LatLng otherLatLng : otherLatLngs) {
                // If the other user is within this user's matching distance
                if (currentUserLatLng.calculateDistance(otherLatLng) <= currentUser.getMatchingDistanceCap()) {
                    List<Integer> otherUserPets = userRepository.fetchUserPets(otherUser.getId());

                    for (int otherPetId : otherUserPets) {
                        // Only add the other user's pet if it's not on this pet's rejected list
                        if (!rejectedPets.contains(otherPetId)) {
                            potentialMatches.add(String.valueOf(otherPetId));
                        }
                    }
                }
            }
        }

        return new ResponseModel(
                true,
                "Successfully generated pet potential matches.",
                new PetMatchesGeneratorResponseModel(potentialMatches)
        );
    }
}