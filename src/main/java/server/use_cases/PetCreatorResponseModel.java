package server.use_cases;

import java.util.Objects;

/**
 * An object defining the response type for PetCreator.createPet.
 */
public class PetCreatorResponseModel {

    private final boolean isSuccess;
    private final String name;
    private final int age;
    private final String petId;

    public PetCreatorResponseModel(boolean isSuccess, String name, int age, String petId) {
        this.isSuccess = isSuccess;
        this.name = name;
        this.age = age;
        this.petId = petId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            PetCreatorResponseModel that = (PetCreatorResponseModel) o;
            return isSuccess == that.isSuccess
                    && Objects.equals(name, that.name)
                    && Objects.equals(age, that.age)
                    && Objects.equals(petId, that.petId);
        }
    }
    public boolean isSuccess() {
        return isSuccess;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPetId() {
        return petId;
    }
}
