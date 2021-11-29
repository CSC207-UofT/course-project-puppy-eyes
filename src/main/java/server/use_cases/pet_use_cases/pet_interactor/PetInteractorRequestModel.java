package server.use_cases.pet_use_cases.pet_interactor;

import server.use_cases.AuthRequestModel;

public class PetInteractorRequestModel extends AuthRequestModel {

    private final String pet1Id;
    private final String pet2Id;
    private final PetInteractionType interactionType;

    public PetInteractorRequestModel(String headerUserId, String pet1Id, String pet2Id, PetInteractionType interactionType) {
        super(headerUserId);
        this.pet1Id = pet1Id;
        this.pet2Id = pet2Id;
        this.interactionType = interactionType;
    }

    public String getFirstPetId() {
        return this.pet1Id;
    }

    public String getSecondPetId() {
        return this.pet2Id;
    }

    public PetInteractionType getInteractionType() {
        return this.interactionType;
    }

}
