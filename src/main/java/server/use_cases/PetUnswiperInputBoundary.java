package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

public interface PetUnswiperInputBoundary {

    public ResponseModel unswipePets(PetUnswiperRequestModel request);

}
