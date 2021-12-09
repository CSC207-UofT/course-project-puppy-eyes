package server.use_cases.pet_use_cases.pet_swipes_fetcher;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetSwipesFetcherRequestModelTest {
    String headerUserId1 = "1";
    String petId1 = "11";
    PetSwipesFetcherRequestModel request = new PetSwipesFetcherRequestModel(headerUserId1, petId1);

    @Test
    void getPetId(){
        assertEquals(petId1, request.getPetId());
    }

}
