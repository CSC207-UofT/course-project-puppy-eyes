package server.use_cases;

import server.use_cases.repo_abstracts.IRelationRepository;

/**
 * A use case responsible for handling a pet "swiping" on another pet
 */
public class PetSwiper implements PetSwiperInputBoundary {

    private final IRelationRepository relationRepository;

    public PetSwiper(IRelationRepository relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public PetSwiperResponseModel swipe(PetSwiperRequestModel request) {
        PetSwiperResponseModel response = new PetSwiperResponseModel(true);

        final String swipeRelationType = "SWIPE";
        final String matchRelationType = "MATCH";

        // If the first pet already swiped on the second pet, do nothing.
        if (this.relationRepository.hasRelation(request.getFirstPetId(), request.getSecondPetId(), swipeRelationType)) {
            return response;
        }

        // If the second pet already swiped on the first pet,
        // remove the first pet from the second pet's swiped list and match both of them
        if (this.relationRepository.hasRelation(request.getSecondPetId(), request.getFirstPetId(), swipeRelationType)) {
            this.relationRepository.removeRelation(request.getSecondPetId(), request.getFirstPetId(), swipeRelationType);
            this.relationRepository.addRelation(request.getFirstPetId(), request.getSecondPetId(), matchRelationType);
            this.relationRepository.addRelation(request.getSecondPetId(), request.getFirstPetId(), matchRelationType);
            return response;
        }

        // If neither pet swiped on each other,
        // add the second pet to the first pet's swiped list
        this.relationRepository.addRelation(request.getFirstPetId(), request.getSecondPetId(), swipeRelationType);
        return response;
    }
}