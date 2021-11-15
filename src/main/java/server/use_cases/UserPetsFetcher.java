package server.use_cases;

import server.entities.User;
import server.use_cases.repo_abstracts.*;

import java.util.List;
import java.util.stream.Collectors;

public class UserPetsFetcher implements UserPetsFetcherInputBoundary {

    private final IUserRepository userRepository;

    public UserPetsFetcher(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a new ResponseModel with given request.
     *
     * @param request Object containing id of the user.
     * @return a ResponseModel listing all the pets.
     */
    @Override
    public ResponseModel fetchUserPets(UserPetsFetcherRequestModel request)  {
        int id;
        try {
            id = Integer.parseInt(request.getUserId());
        } catch (NumberFormatException e) {
            // Invalid user id
            return new ResponseModel(false, "ID must be an integer.");
        }

        try {
            User user = userRepository.fetchUser(id);

            if (!request.isRequestAuthorized()) {
                return new ResponseModel(false, "You are not authorized to make this request.");
            }

            // Convert integers to strings
            List<String> stringPetIds = user.getPetList().stream().
                    map(String::valueOf).
                    collect(Collectors.toList());

            return new ResponseModel(
                true,
                "Successfully fetched user pets.",
                new UserPetsFetcherResponseModel(stringPetIds)
            );
        } catch (UserNotFoundException exception) {
            return new ResponseModel(false, "User with ID: " + id + " does not exist.");
        }
    }

}
