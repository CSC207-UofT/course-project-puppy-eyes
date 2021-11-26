package server.use_cases.pet_matches_generator;
import server.entities.User;
import server.use_cases.ResponseModel;
import server.use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_action_validator.PetActionValidatorRequestModel;
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
//    private final IGeocoderService geocoderService;

    public PetMatchesGenerator(IUserRepository userRepository,
                               IPetRepository petRepository,
                               PetActionValidatorInputBoundary petActionValidator
                                /*,IGeocoderService geocoderService*/) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        this.petActionValidator = petActionValidator;
//        this.geocoderService = geocoderService;
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

        // For now, return ALL pets that both
        // - don't belong to the pet's user
        // - are not on the pet's reject list

        List<User> users = this.userRepository.fetchAllUsers();
        List<Integer> rejectedPets = this.petRepository.fetchRejected(petId);

        for (User user : users) {
            if (user.getId() == currentUser.getId())
                continue;

            List<Integer> petIds = this.userRepository.fetchUserPets(user.getId());

            for (int id : petIds) {
                if (rejectedPets.contains(id))
                    continue;

                potentialMatches.add(String.valueOf(id));
            }
        }

        // TODO geocoding... in Phase 2
//            // GeocoderService.getLatLng returns a List of LatLng objects, but here we are assuming that the current user's
//            // current address and city are sufficiently specific to return a unique latitude-longitude tuple
//            LatLng currentUserLatLng = this.geocoderService.getLatLng(currentUser.getCurrentAddress() + ", " + currentUser.getCurrentCity()).get(0);
//
//            // Fetch all users from the database
//            List<User> users = this.userRepository.fetchAllUsers();
//
//            for (User user : users) {
//                System.out.println(user.getId());
//                System.out.println(user.getFirstName());
//
//                List<LatLng> otherLatLngs = geocoderService.getLatLng(user.getCurrentAddress() + ", " + user.getCurrentCity());
//
//                for (LatLng otherLatLng : otherLatLngs) {
//                    // If the other user is within this user's matching distance
//                    if (currentUserLatLng.calculateDistance(otherLatLng) <= currentUser.getMatchingDistanceCap()) {
//                        // for now, add every pet of the other user as a potential match
//                        // later, include filtering options
//                        potentialMatches.addAll(user.getPetList().stream().map(String::valueOf).collect(Collectors.toList()));
//                    }
//                }
//            }

        return new ResponseModel(
                true,
                "Successfully generated pet potential matches.",
                new PetMatchesGeneratorResponseModel(potentialMatches)
        );
    }
}