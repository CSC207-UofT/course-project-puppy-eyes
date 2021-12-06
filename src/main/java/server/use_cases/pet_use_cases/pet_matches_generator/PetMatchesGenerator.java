package server.use_cases.pet_use_cases.pet_matches_generator;
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

    public PetMatchesGenerator(IUserRepository userRepository,
                               IPetRepository petRepository,
                               PetActionValidatorInputBoundary petActionValidator) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        this.petActionValidator = petActionValidator;
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
        int userId = petRepository.fetchPet(petId).getUserId();

        List<String> potentialMatches = new ArrayList<>();

        // Fetch all users from the database
        List<User> users = this.userRepository.fetchAllUsers();

        // currentUser will not be null, or else the action validator would have failed
        User currentUser = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);

        List<Integer> rejectedPets = this.petRepository.fetchRejected(petId);
        List<Integer> matchedPets = this.petRepository.fetchMatches(petId);
        List<Integer> swipedPets = this.petRepository.fetchSwipedOn(petId);

        LatLng currUserLatLng = new LatLng(Double.valueOf(currentUser.getLat()), Double.valueOf(currentUser.getLng()));

        for (User otherUser : users) {
            // Do not fetch pets for the same user
            if (otherUser.getId() == currentUser.getId())
                continue;

            LatLng otherLatLng = new LatLng(Double.valueOf(otherUser.getLat()), Double.valueOf(otherUser.getLng()));

            // Check if the other user is within this user's matching distance
            if (currUserLatLng.calculateDistance(otherLatLng) <= currentUser.getMatchingDistanceCap()) {
                List<Integer> otherUserPets = userRepository.fetchUserPets(otherUser.getId());

                for (int otherPetId : otherUserPets) {
                    // Ignore pet if on the rejected list or has already matched or swiped
                    if (!rejectedPets.contains(otherPetId) || !matchedPets.contains(otherPetId) || !swipedPets.contains(otherPetId)) {
                        potentialMatches.add(String.valueOf(otherPetId));
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