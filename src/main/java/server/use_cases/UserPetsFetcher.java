package server.use_cases;

import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.IUserRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class UserPetsFetcher implements UserPetsFetcherInputBoundary {

    private final IUserRepository userRepository;

    public UserPetsFetcher(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a new UserPetsFetcher with given request.
     *
     * @param request Object containing id of the user.
     * @return a UserPetsFetcherResponseModel listing all the pets .
     */
    @Override
    public UserPetsFetcherResponseModel fetchUserPets(UserPetsFetcherRequestModel request)  {
        int id;
        try {
            id = Integer.parseInt(request.getUserId());
        } catch (NumberFormatException e) {
            // Invalid pet id
            return new UserPetsFetcherResponseModel(false, null);
        }

        try {
            List<Integer> petIds = userRepository.fetchUserPets(id);
            // Convert integers to strings
            List<String> stringPetIds = petIds.stream().map(String::valueOf).collect(Collectors.toList());

            return new UserPetsFetcherResponseModel(true, stringPetIds);
        } catch (UserNotFoundException exception) {
            return new UserPetsFetcherResponseModel(false, null);
        }
    }

}
