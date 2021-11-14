package server.use_cases;

import server.use_cases.repo_abstracts.AuthRequestModel;

public class PetSwiperRequestModel extends AuthRequestModel {

    private int pet1Id;
    private int pet2Id;

    public PetSwiperRequestModel(String headerUserId, int pet1Id, int pet2Id) {
        super(headerUserId);
        this.pet1Id = pet1Id;
        this.pet2Id = pet2Id;
    }

    public int getFirstPetId() {
        return this.pet1Id;
    }

    public int getSecondPetId() {
        return this.pet2Id;
    }

}
