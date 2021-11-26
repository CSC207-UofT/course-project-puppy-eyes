package server.use_cases.pet_editor;

import server.entities.Pet;
import server.use_cases.Util;
import server.use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_action_validator.PetActionValidatorRequestModel;
import server.use_cases.pet_profile_validator.PetProfileValidatorInputBoundary;
import server.use_cases.pet_profile_validator.PetProfileValidatorRequestModel;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.ResponseModel;

/**
 * A use case responsible for editing a pet.
 */
public class PetEditor implements PetEditorInputBoundary {

    private IPetRepository petRepository;
    private PetProfileValidatorInputBoundary petProfileValidator;
    private PetActionValidatorInputBoundary petActionValidator;

    public PetEditor(IPetRepository petRepository,
                     PetProfileValidatorInputBoundary petProfileValidator,
                     PetActionValidatorInputBoundary petActionValidator) {
        this.petRepository = petRepository;
        this.petProfileValidator = petProfileValidator;
        this.petActionValidator = petActionValidator;
    }

    /**
     * Create a PetEditorResponseModel with given request.
     *
     * @param request Object containing new information of the pet;
     * @return a PetEditorResponseModel that contains the edited information of the pet.
     */
    @Override
    public ResponseModel editPet(PetEditorRequestModel request) {
        ResponseModel validateActionResponse = petActionValidator.validateAction(new PetActionValidatorRequestModel(
            request.getHeaderUserId(), request.getPetId()
        ));

        // Check if the action is validated
        if (!validateActionResponse.isSuccess()) {
            return validateActionResponse;
        }

        // Check if use case inputs are in valid form
        if (request.getNewAge() != null && !Util.isInteger(request.getNewAge())) {
            return new ResponseModel(false, "Age must be an integer.");
        }

        int petId = Integer.parseInt(request.getPetId());
        Pet pet = petRepository.fetchPet(petId);

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
