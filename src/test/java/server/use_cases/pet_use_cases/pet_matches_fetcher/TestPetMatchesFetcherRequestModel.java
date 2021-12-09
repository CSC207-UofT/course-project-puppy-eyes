package server.use_cases.pet_use_cases.pet_matches_fetcher;

import org.junit.jupiter.api.Test;

public class TestPetMatchesFetcherRequestModel {
    PetMatchesFetcherRequestModel request = new PetMatchesFetcherRequestModel("User1", "12");

    @Test
    public void TestSuccessPetMatchesFetcherRequestModel(){
        assert request.getPetId().equals("12");


    }


    @Test
    public void TestFailurePetMatchesFetcherRequestModel(){
        assert !request.getPetId().equals("1");

    }

}
