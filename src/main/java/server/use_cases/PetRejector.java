package server.use_cases;

import server.use_cases.repo_abstracts.IRelationRepository;

/**
 * A use case responsible for handling a pet "rejecting" another pet.
 */
public class PetRejector implements PetRejectorInputBoundary {

    private final IRelationRepository relationRepository;

    public PetRejector(IRelationRepository relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public PetRejectorResponseModel rejectPets(PetRejectorRequestModel request) {
        PetRejectorResponseModel response = new PetRejectorResponseModel(true);

        final String rejectRelationType = "REJECT";
        final String swipeRelationType = "SWIPE";
        final String matchRelationType = "MATCH";

        // Do nothing if the pets are already matched
        if (this.relationRepository.hasRelation(request.getFirstPetId(), request.getSecondPetId(), matchRelationType)
                || this.relationRepository.hasRelation(request.getSecondPetId(), request.getFirstPetId(), matchRelationType)) {
            return response;
        }

        // Remove both pets from each other's swiped list
        this.relationRepository.removeRelation(request.getFirstPetId(), request.getSecondPetId(), swipeRelationType);
        this.relationRepository.removeRelation(request.getSecondPetId(), request.getFirstPetId(), swipeRelationType);

        // Add the second pet to the first pet's rejected list
        this.relationRepository.addRelation(request.getFirstPetId(), request.getSecondPetId(), rejectRelationType);

        return response;
    }

}