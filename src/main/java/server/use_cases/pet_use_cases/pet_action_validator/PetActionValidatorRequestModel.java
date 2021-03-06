package server.use_cases.pet_use_cases.pet_action_validator;

import server.use_cases.AuthRequestModel;

public class PetActionValidatorRequestModel extends AuthRequestModel {

    private final String petId;
    private String petId2;

    public PetActionValidatorRequestModel(boolean fromTerminal, String headerUserId, String petId) {
        super(headerUserId);
        this.petId = petId;
        this.setFromTerminal(fromTerminal);
    }

    public PetActionValidatorRequestModel(boolean fromTerminal, String headerUserId, String petId1, String petId2) {
        super(headerUserId);
        this.petId = petId1;
        this.petId2 = petId2;
        this.setFromTerminal(fromTerminal);
    }

    public String getPetId() {
        return this.petId;
    }

    public String getSecondPetId() {
        return this.petId2;
    }

}
