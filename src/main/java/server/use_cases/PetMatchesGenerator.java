package server.use_cases;
import server.drivers.IGeocoderService;
import server.drivers.LatLng;
import server.entities.Pet;
import server.entities.User;
import server.use_cases.repo_abstracts.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A use case responsible for generating a list of matches for a given pet.
 */
public class PetMatchesGenerator implements PetMatchesGeneratorInputBoundary {

    private final IUserRepository userRepository;
    private final IPetRepository petRepository;
    //private final IGeocoderService geocoderService;

    public PetMatchesGenerator(IUserRepository userRepository, IPetRepository petRepository) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        //this.geocoderService = geocoderService;
    }

    @Override
    public ResponseModel generatePotentialMatches(PetMatchesGeneratorRequestModel request) {
        int id;
        try {
            id = Integer.parseInt(request.getPetId());
        } catch (NumberFormatException e) {
            // Invalid pet id
            return new ResponseModel(false, "ID must be an integer.");
        }

        try {
            Pet pet = petRepository.fetchPet(id);
            request.setUserId(String.valueOf(pet.getUserId()));

            if (!request.isRequestAuthorized()) {
                return new ResponseModel(false, "You are not authorized to make this request.");
            }

            List<String> potentialMatches = new ArrayList<>();

            User currentUser;

            try {
                currentUser = userRepository.fetchUser(pet.getUserId());
            } catch (UserNotFoundException exception) {
                return new ResponseModel(false, "User with ID: " + pet.getUserId() + " does not exist.");
            }

            // For now, return ALL pets that both
            // - don't belong to the pet's user
            // - are not on the pet's reject list

            List<User> users = this.userRepository.fetchAllUsers();

            for (User user : users) {
                if (user.getId() == currentUser.getId())
                    continue;

                for (int petId : user.getPetList()) {
                    if (pet.getRejected().contains(petId))
                        continue;

                    potentialMatches.add(String.valueOf(petId));
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
        } catch (PetNotFoundException e) {
            // Pet not found
            return new ResponseModel(false, "Pet with ID: " + request.getPetId() + " does not exist.");
        }
    }
}