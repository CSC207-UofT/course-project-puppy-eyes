package server.use_cases;

import server.entities.Pet;
import server.use_cases.repo_abstracts.IPetRepository;
import server.use_cases.repo_abstracts.PetNotFoundException;
import server.use_cases.repo_abstracts.ResponseModel;

/**
 * A use case responsible for editing a pet.
 */
public class PetEditor implements PetEditorInputBoundary {
    IPetRepository petRepository;

    public PetEditor(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Create a PetEditorResponseModel with given request.
     *
     * @param request Object containing new information of the pet;
     * @return a PetEditorResponseModel that contains the edited information of the pet.
     */
    @Override
    public ResponseModel editPet(PetEditorRequestModel request) {
        int petId;

        try {
            petId = Integer.parseInt(request.getPetId());
        } catch (NumberFormatException e) {
            // Invalid pet id
            return new ResponseModel(false, "ID must be an integer.");
        }

        Pet pet;

        try {
            pet = petRepository.fetchPet(petId);
        } catch (PetNotFoundException exception) {
            return new ResponseModel(false, "Pet with ID: " + request.getPetId() + " does not exist.");
        }

        pet.setName(request.getNewName());
        pet.setAge(request.getNewAge());
        pet.setBiography(request.getNewBiography());

        // Check for valid inputs
        if (!pet.isNameValid()) {
            return new ResponseModel(false, "Please enter a name of at least 3 characters.");
        }

        if (!pet.isAgeValid()) {
            return new ResponseModel(false, "Please enter a non-negative age.");
        }

        if (!pet.isBreedValid()) {
            return new ResponseModel(false, "Please enter a breed of at least 3 characters.");
        }

        boolean isSuccess = petRepository.editPet(
                petId,
                request.getNewName(),
                request.getNewAge(),
                request.getNewBreed(),
                request.getNewBiography()
        );

        if (!isSuccess) {
            return new ResponseModel(false, "An unexpected error occurred.");
        }

        return new ResponseModel(
            true,
            "Successfully edited pet.",
            new PetEditorResponseModel(
                request.getNewName(),
                request.getNewAge(),
                request.getNewBreed(),
                request.getNewBiography(),
                String.valueOf(request.getPetId())
            )
        );
    }
}
