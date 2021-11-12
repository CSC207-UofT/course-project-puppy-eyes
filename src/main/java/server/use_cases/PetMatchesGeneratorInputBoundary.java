package server.use_cases;

import java.io.IOException;

public interface PetMatchesGeneratorInputBoundary {

    public PetMatchesGeneratorResponseModel generatePotentialMatches(PetMatchesGeneratorRequestModel request) throws IOException, InterruptedException;

}
