package server.use_cases;

import server.use_cases.repo_abstracts.IPetRepository;

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
    public PetEditorResponseModel editPet(PetEditorRequestModel request) {
        boolean isSuccess = petRepository.editPet(Integer.parseInt(request.getPetId()),
                request.getNewName(), request.getNewAge(),
                request.getNewBreed(), request.getNewBiography());

        return new PetEditorResponseModel(isSuccess,
                request.getNewName(),
                request.getNewAge(),
                request.getNewBreed(),
                request.getNewBiography(),
                request.getPetId());
    }
}
