package server.use_cases;

import server.use_cases.repo_abstracts.IRelationRepository;

/**
 * A use case responsible for handling a pet "unswiping" on another pet
 */
public class PetUnswiper implements PetUnswiperInputBoundary {

    private final IRelationRepository relationRepository;

    public PetUnswiper(IRelationRepository relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public PetUnswiperResponseModel unswipePets(PetUnswiperRequestModel request) {
        PetUnswiperResponseModel response = new PetUnswiperResponseModel(true);

        final String relationType = "SWIPE";

        // If the first pet already swiped on the second pet, remove the second pet from the first pet's swiped list
        if (this.relationRepository.hasRelation(request.getFirstPetId(), request.getSecondPetId(), relationType)) {
            this.relationRepository.removeRelation(request.getFirstPetId(), request.getSecondPetId(), relationType);
        }

        return response;
    }

}