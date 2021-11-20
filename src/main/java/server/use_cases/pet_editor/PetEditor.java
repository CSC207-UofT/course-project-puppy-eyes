package server.use_cases.pet_editor;

import server.entities.Pet;
import server.use_cases.pet_profile_validator.PetProfileValidatorInputBoundary;
import server.use_cases.pet_profile_validator.PetProfileValidatorRequestModel;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.ResponseModel;

import java.util.regex.Pattern;

/**
 * A use case responsible for editing a pet.
 */
public class PetEditor implements PetEditorInputBoundary {

    private IPetRepository petRepository;
    private PetProfileValidatorInputBoundary petProfileValidator;

    public PetEditor(IPetRepository petRepository, PetProfileValidatorInputBoundary petProfileValidator) {
        this.petRepository = petRepository;
        this.petProfileValidator = petProfileValidator;
    }

    /**
     * Create a PetEditorResponseModel with given request.
     *
     * @param request Object containing new information of the pet;
     * @return a PetEditorResponseModel that contains the edited information of the pet.
     */
    @Override
    public ResponseModel editPet(PetEditorRequestModel request) {
        String intRegex = "/^[-+]?\\d+$/";
        Pattern intPattern = Pattern.compile(intRegex);

        // null checks
        if (request.getPetId() == null || request.getUserId() == null) {
            return new ResponseModel(false, "Missing required fields.");
        }

        // Check if the request fields are in the valid datatype
        if (!intPattern.matcher(request.getPetId()).matches()
                || !intPattern.matcher(request.getUserId()).matches()
                || !intPattern.matcher(request.getHeaderUserId()).matches()) {
            return new ResponseModel(false, "ID must be an integer.");
        }

        if (request.getNewAge() != null && !intPattern.matcher(request.getNewAge()).matches()) {
            return new ResponseModel(false, "Age must be an integer.");
        }

        int petId = Integer.parseInt(request.getPetId());

        Pet pet;

        try {
            pet = petRepository.fetchPet(petId);
        } catch (PetNotFoundException exception) {
            return new ResponseModel(false, "Pet with ID: " + request.getPetId() + " does not exist.");
        }

        // Do not modify null fields
        String newName = request.getNewName() == null ? pet.getName() : request.getNewName();
        String newAge = request.getNewAge() == null ? pet.getAge() + "" : request.getNewAge();
        String newBreed = request.getNewBreed() == null ? pet.getBreed() : request.getNewBreed();
        String newBiography = request.getNewBiography() == null ? pet.getBiography() : request.getNewBiography();

        // Check for valid inputs
        ResponseModel verifyInputsResponse = petProfileValidator.validateProfile(new PetProfileValidatorRequestModel(
            newName, newAge, newBreed, newBiography
        ));

        if (!verifyInputsResponse.isSuccess()) {
            return verifyInputsResponse;
        }

        pet.setName(newName);
        pet.setAge(Integer.parseInt(newAge));
        pet.setBreed(newBreed);
        pet.setBiography(newBiography);

        boolean isSuccess = petRepository.editPet(
            petId,
            newName,
            Integer.parseInt(newAge),
            newBreed,
            newBiography
        );

        if (!isSuccess) {
            return new ResponseModel(false, "An unexpected error occurred.");
        }

        return new ResponseModel(
            true,
            "Successfully edited pet.",
            new PetEditorResponseModel(
                newName,
                Integer.parseInt(newAge),
                newBreed,
                newBiography,
                request.getPetId()
            )
        );
    }
}
