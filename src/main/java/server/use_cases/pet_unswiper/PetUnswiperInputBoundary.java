package server.use_cases.pet_unswiper;

import server.use_cases.repo_abstracts.ResponseModel;

public interface PetUnswiperInputBoundary {

    public ResponseModel unswipePets(PetUnswiperRequestModel request);

}
