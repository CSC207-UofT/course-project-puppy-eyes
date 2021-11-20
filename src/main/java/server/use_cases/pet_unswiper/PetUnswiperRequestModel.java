package server.use_cases.pet_unswiper;

import server.use_cases.repo_abstracts.AuthRequestModel;

public class PetUnswiperRequestModel extends AuthRequestModel {

    private String pet1Id;
    private String pet2Id;

    public PetUnswiperRequestModel(String headerUserId, String pet1Id, String pet2Id) {
        super(headerUserId);
        this.pet1Id = pet1Id;
        this.pet2Id = pet2Id;
    }

    public String getFirstPetId() {
        return this.pet1Id;
    }

    public String getSecondPetId() {
        return this.pet2Id;
    }

}