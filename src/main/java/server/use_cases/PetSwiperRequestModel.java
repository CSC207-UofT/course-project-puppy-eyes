package server.use_cases;

import server.entities.Pet;

public class PetSwiperRequestModel {

    private final int pet1Id;
    private final int pet2Id;

    public PetSwiperRequestModel(int pet1Id, int pet2Id) {
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
