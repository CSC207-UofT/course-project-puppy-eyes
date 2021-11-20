package server.use_cases.pet_creator;

import server.entities.Pet;
import server.use_cases.pet_profile_validator.PetProfileValidatorInputBoundary;
import server.use_cases.pet_profile_validator.PetProfileValidatorRequestModel;
import server.use_cases.repo_abstracts.*;

import java.util.regex.Pattern;

/**
 * A use case responsible for creating new Pet.
 */
public class PetCreator implements PetCreatorInputBoundary {

    private final IPetRepository petRepository;
    private final IUserRepository userRepository;
    private final PetProfileValidatorInputBoundary petProfileValidator;

    public PetCreator(IPetRepository petRepository, IUserRepository userRepository,
                      PetProfileValidatorInputBoundary petProfileValidator) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
        this.petProfileValidator = petProfileValidator;
    }

    /**
     * Create a new PetCreatorResponseModel with given request.
     *
     * @param request Object containing registration data of the new pet.
     * @return a PetCreatorResponseModel that contains the created pet's basic information.
     */
    public ResponseModel createPet(PetCreatorRequestModel request) {
        String intRegex = "/^[-+]?\\d+$/";
        Pattern intPattern = Pattern.compile(intRegex);

        // null checks
        if (request.getUserId() == null || request.getName() == null || request.getBreed() == null || request.getAge() == null ) {
            return new ResponseModel(false, "Missing required fields.");
        }

        // Check if the request fields are in the valid datatype
        if (!intPattern.matcher(request.getUserId()).matches() || !intPattern.matcher(request.getHeaderUserId()).matches()) {
            return new ResponseModel(false, "ID must be an integer.");
        }

        if (request.getAge() != null && !intPattern.matcher(request.getAge()).matches()) {
            return new ResponseModel(false, "Age must be an integer.");
        }

        // Check if the request fields pass logic checks
        ResponseModel verifyInputsResponse = petProfileValidator.validateProfile(new PetProfileValidatorRequestModel(
                request.getName(), request.getAge(), request.getBreed(), request.getBiography()
        ));

        if (!verifyInputsResponse.isSuccess()) {
            return verifyInputsResponse;
        }
        int userId = Integer.parseInt(request.getUserId());
        int intAge = Integer.parseInt(request.getAge());

        // Check if user exists
        try {
            userRepository.fetchUser(userId);
        } catch (UserNotFoundException exception) {
            return new ResponseModel(false, "User with ID: " + userId + " does not exist.");
        }

        if (!request.isRequestAuthorized()) {
            return new ResponseModel(false, "You are not authorized to make this request.");
        }

        Pet newPet = new Pet(userId, request.getName(), intAge, request.getBreed(), request.getBiography() == null ? "" : request.getBiography()) {};

        int petId = petRepository.createPet(userId, newPet.getName(), newPet.getAge(), newPet.getBreed(), newPet.getBiography());
        newPet.setId(petId);

        return new ResponseModel(true, "Pet created successfully.",
                new PetCreatorResponseModel(
                        String.valueOf(petId),
                        request.getUserId(),
                        newPet.getName(),
                        String.valueOf(newPet.getAge()),
                        newPet.getBreed(),
                        newPet.getBiography()
                )
        );
    }
}
