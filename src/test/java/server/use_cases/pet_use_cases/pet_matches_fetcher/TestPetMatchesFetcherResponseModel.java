package server.use_cases.pet_use_cases.pet_matches_fetcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;


public class TestPetMatchesFetcherResponseModel {


    public void setUp(List<String> petIDS) {
        petIDS.add("1");
        petIDS.add("2");
        petIDS.add("3");
        petIDS.add("45");

    }

    @Test
    public void TestSuccessPetMatchesFetcherResponseModel() {
        List<String> petIDs = new ArrayList<String>();
        List<String> testing = new ArrayList<String>();

        setUp(petIDs);
        setUp(testing);
        PetMatchesFetcherResponseModel response = new PetMatchesFetcherResponseModel(petIDs);
        assert response.getPetIds().equals(testing);

    }

    @Test
    public void TestFailurePetMatchesFetcherResponseModel() {
        List<String> petIDs = new ArrayList<String>();
        List<String> testing = new ArrayList<String>();

        setUp(petIDs);
        setUp(testing);
        testing.add("12");
        PetMatchesFetcherResponseModel response = new PetMatchesFetcherResponseModel(petIDs);
        assert !response.getPetIds().equals(testing);

    }

}
