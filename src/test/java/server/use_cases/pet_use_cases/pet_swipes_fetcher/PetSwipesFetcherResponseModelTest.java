package server.use_cases.pet_use_cases.pet_swipes_fetcher;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class PetSwipesFetcherResponseModelTest {
    List<String> petIds1 = List.of("1", "11", "111", "1111");


    PetSwipesFetcherResponseModel response = new PetSwipesFetcherResponseModel(petIds1);

    @Test
    void getPetIds() {
        assertEquals(petIds1, response.getPetIds());
    }
}
