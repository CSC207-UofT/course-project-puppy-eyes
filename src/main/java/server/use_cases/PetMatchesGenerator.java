package server.use_cases;

import server.drivers.GeocoderService;
import server.drivers.LatLng;
import server.entities.Pet;
import server.entities.User;
import server.use_cases.repo_abstracts.IUserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public PetMatchesGeneratorResponseModel generatePotentialMatches(PetMatchesGeneratorRequestModel request) {
        List<Pet> potentialMatches = new ArrayList<>();
        boolean isSuccess = true;

        User currentUser = request.getUser();
        // GeocoderService.getLatLng returns a List of LatLng objects, but here we are assuming that the current user's
        // current address and city are sufficiently specific to return a unique latitude-longitude tuple
        try {
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
                        potentialMatches.addAll(user.getPetList());
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            isSuccess = false;
        }

        PetMatchesGeneratorResponseModel response = new PetMatchesGeneratorResponseModel(isSuccess, potentialMatches);
        return response;
    }
}