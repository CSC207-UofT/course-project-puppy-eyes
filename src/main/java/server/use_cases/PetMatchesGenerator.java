package server.use_cases;

import server.drivers.GeocoderService;
import server.drivers.LatLng;
import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.ResponseModel;
import server.use_cases.repo_abstracts.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A use case responsible for generating a list of matches for a given pet.
 */
public class PetMatchesGenerator implements PetMatchesGeneratorInputBoundary {

    private final IUserRepository userRepository;
    private final GeocoderService geocoderService;

    public PetMatchesGenerator(IUserRepository userRepository, GeocoderService geocoderService) {
        this.userRepository = userRepository;
        this.geocoderService = geocoderService;
    }

    @Override
    public ResponseModel generatePotentialMatches(PetMatchesGeneratorRequestModel request) {
        List<String> potentialMatches = new ArrayList<>();

        if (!request.isRequestAuthorized()) {
            return new ResponseModel(false, "You are not authorized to make this request.");
        }

        int userId;

        try {
            userId = Integer.parseInt(request.getUserId());
        } catch (NumberFormatException exception) {
            return new ResponseModel(false, "ID must be an integer");
        }

        User currentUser;

        try {
            currentUser = userRepository.fetchUser(userId);
        } catch (UserNotFoundException exception) {
            return new ResponseModel(false, "User with ID: " + userId + " does not exist.");
        }

        // GeocoderService.getLatLng returns a List of LatLng objects, but here we are assuming that the current user's
        // current address and city are sufficiently specific to return a unique latitude-longitude tuple
        LatLng currentUserLatLng = this.geocoderService.getLatLng(currentUser.getCurrentAddress() + ", " + currentUser.getCurrentCity()).get(0);

        // Fetch all users from the database
        List<User> users = this.userRepository.fetchAllUsers();

        for (User user : users) {
            List<LatLng> otherLatLngs = geocoderService.getLatLng(user.getCurrentAddress() + ", " + user.getCurrentCity());

            for (LatLng otherLatLng : otherLatLngs) {
                // If the other user is within this user's matching distance
                if (currentUserLatLng.calculateDistance(otherLatLng) <= currentUser.getMatchingDistanceCap()) {
                    // for now, add every pet of the other user as a potential match
                    // later, include filtering options
                    potentialMatches.addAll(user.getPetList().stream().map(String::valueOf).collect(Collectors.toList()));
                }
            }
        }

        return new ResponseModel(
            true,
            "Successfully generated pet potential matches.",
            new PetMatchesFetcherResponseModel(potentialMatches)
        );
    }
}